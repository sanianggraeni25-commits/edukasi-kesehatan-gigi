package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DentalDataSource
import com.example.model.AppScreen
import com.example.model.DentalTip
import com.example.model.DentalTopic
import com.example.model.QuizQuestion
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DentalUiState(
    val currentScreen: AppScreen = AppScreen.HOME,
    val screenHistory: List<AppScreen> = listOf(AppScreen.HOME),
    val selectedTopic: DentalTopic? = null,
    val topicSearchQuery: String = "",
    val selectedTopicCategory: String = "Semua",
    // Tips
    val selectedTipCategory: String = "Semua",
    val bookmarkedTipIds: Set<String> = setOf("tip_1", "tip_2"),
    // Timer
    val isTimerRunning: Boolean = false,
    val timerRemainingSeconds: Int = 120,
    val timerTotalSeconds: Int = 120,
    val timerCompleted: Boolean = false,
    // Quiz
    val currentQuizIndex: Int = 0,
    val selectedAnswers: Map<Int, Int> = emptyMap(),
    val isCurrentAnswerSubmitted: Boolean = false,
    val quizFinished: Boolean = false,
    val quizScore: Int = 0,
    val factIndex: Int = 0
)

class DentalViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DentalUiState())
    val uiState: StateFlow<DentalUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        // Rotate random fact
        _uiState.update { it.copy(factIndex = (0 until DentalDataSource.quickDentalFacts.size).random()) }
    }

    fun navigateTo(screen: AppScreen, topic: DentalTopic? = null) {
        _uiState.update { current ->
            val newHistory = if (current.currentScreen != screen) {
                current.screenHistory + screen
            } else {
                current.screenHistory
            }
            current.copy(
                currentScreen = screen,
                screenHistory = newHistory,
                selectedTopic = topic ?: current.selectedTopic
            )
        }
    }

    fun navigateBack(): Boolean {
        val current = _uiState.value
        if (current.screenHistory.size > 1) {
            val updatedHistory = current.screenHistory.dropLast(1)
            val prevScreen = updatedHistory.last()
            _uiState.update {
                it.copy(
                    currentScreen = prevScreen,
                    screenHistory = updatedHistory
                )
            }
            return true
        }
        return false
    }

    fun openTopicDetail(topic: DentalTopic) {
        navigateTo(AppScreen.TOPIC_DETAIL, topic)
    }

    fun setTopicSearchQuery(query: String) {
        _uiState.update { it.copy(topicSearchQuery = query) }
    }

    fun setTopicCategory(category: String) {
        _uiState.update { it.copy(selectedTopicCategory = category) }
    }

    fun setTipCategory(category: String) {
        _uiState.update { it.copy(selectedTipCategory = category) }
    }

    fun toggleTipBookmark(tipId: String) {
        _uiState.update { current ->
            val updated = if (current.bookmarkedTipIds.contains(tipId)) {
                current.bookmarkedTipIds - tipId
            } else {
                current.bookmarkedTipIds + tipId
            }
            current.copy(bookmarkedTipIds = updated)
        }
    }

    // --- Brushing Timer Logic ---
    fun toggleTimer() {
        if (_uiState.value.isTimerRunning) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    fun startTimer() {
        if (_uiState.value.timerRemainingSeconds <= 0) {
            resetTimer()
        }
        _uiState.update { it.copy(isTimerRunning = true, timerCompleted = false) }
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.isTimerRunning && _uiState.value.timerRemainingSeconds > 0) {
                delay(1000L)
                _uiState.update { state ->
                    val nextSec = state.timerRemainingSeconds - 1
                    if (nextSec <= 0) {
                        state.copy(
                            timerRemainingSeconds = 0,
                            isTimerRunning = false,
                            timerCompleted = true
                        )
                    } else {
                        state.copy(timerRemainingSeconds = nextSec)
                    }
                }
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        _uiState.update { it.copy(isTimerRunning = false) }
    }

    fun resetTimer() {
        timerJob?.cancel()
        _uiState.update {
            it.copy(
                isTimerRunning = false,
                timerRemainingSeconds = 120,
                timerCompleted = false
            )
        }
    }

    // --- Quiz Logic ---
    fun selectQuizAnswer(optionIndex: Int) {
        val current = _uiState.value
        if (current.isCurrentAnswerSubmitted) return

        val currentQuestion = DentalDataSource.quizQuestions.getOrNull(current.currentQuizIndex) ?: return
        _uiState.update {
            it.copy(
                selectedAnswers = it.selectedAnswers + (currentQuestion.id to optionIndex)
            )
        }
    }

    fun submitQuizAnswer() {
        val current = _uiState.value
        val currentQuestion = DentalDataSource.quizQuestions.getOrNull(current.currentQuizIndex) ?: return
        val chosen = current.selectedAnswers[currentQuestion.id] ?: return

        val isCorrect = chosen == currentQuestion.correctOptionIndex
        val addedScore = if (isCorrect) 100 / DentalDataSource.quizQuestions.size else 0

        _uiState.update {
            it.copy(
                isCurrentAnswerSubmitted = true,
                quizScore = it.quizScore + addedScore
            )
        }
    }

    fun nextQuizQuestion() {
        val current = _uiState.value
        val nextIndex = current.currentQuizIndex + 1
        if (nextIndex < DentalDataSource.quizQuestions.size) {
            _uiState.update {
                it.copy(
                    currentQuizIndex = nextIndex,
                    isCurrentAnswerSubmitted = false
                )
            }
        } else {
            _uiState.update {
                it.copy(quizFinished = true)
            }
        }
    }

    fun restartQuiz() {
        _uiState.update {
            it.copy(
                currentQuizIndex = 0,
                selectedAnswers = emptyMap(),
                isCurrentAnswerSubmitted = false,
                quizFinished = false,
                quizScore = 0
            )
        }
    }

    fun refreshFact() {
        _uiState.update {
            val nextIdx = (it.factIndex + 1) % DentalDataSource.quickDentalFacts.size
            it.copy(factIndex = nextIdx)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
