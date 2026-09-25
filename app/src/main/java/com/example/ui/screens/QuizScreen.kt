package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SentimentSatisfiedAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DentalDataSource
import com.example.model.QuizQuestion
import com.example.ui.theme.BorderSoft
import com.example.ui.theme.MintAccent
import com.example.ui.theme.MintSoft
import com.example.ui.theme.MintUltraLight
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealPrimaryDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.DentalUiState

@Composable
fun QuizScreen(
    uiState: DentalUiState,
    onSelectOption: (Int) -> Unit,
    onSubmitAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onRestartQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiState.quizFinished) {
        QuizResultView(
            score = uiState.quizScore,
            totalQuestions = DentalDataSource.quizQuestions.size,
            onRestart = onRestartQuiz,
            modifier = modifier
        )
    } else {
        val currentQuestion = DentalDataSource.quizQuestions.getOrNull(uiState.currentQuizIndex)
        if (currentQuestion == null) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Soal tidak tersedia.", color = TextSecondary)
            }
            return
        }

        val total = DentalDataSource.quizQuestions.size
        val progress = (uiState.currentQuizIndex + 1).toFloat() / total.toFloat()
        val selectedOption = uiState.selectedAnswers[currentQuestion.id]

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag("quiz_screen"),
            contentPadding = PaddingValues(16.dp)
        ) {
            // --- Progress Indicator ---
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pertanyaan ${uiState.currentQuizIndex + 1} dari $total",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TealPrimaryDark,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        text = "Skor: ${uiState.quizScore}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TealPrimary,
                            fontSize = 13.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = TealPrimary,
                    trackColor = BorderSoft
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // --- Question Card ---
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderSoft, RoundedCornerShape(20.dp))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MintSoft,
                            modifier = Modifier.padding(bottom = 10.dp)
                        ) {
                            Text(
                                text = "Soal #${uiState.currentQuizIndex + 1}",
                                color = TealPrimaryDark,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Text(
                            text = currentQuestion.question,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                fontSize = 16.sp,
                                lineHeight = 24.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
            }

            // --- Options List ---
            items(currentQuestion.options.size) { index ->
                val optionText = currentQuestion.options[index]
                val isSelected = selectedOption == index
                val isCorrect = index == currentQuestion.correctOptionIndex

                QuizOptionCard(
                    optionLetter = when (index) {
                        0 -> "A"
                        1 -> "B"
                        2 -> "C"
                        else -> "D"
                    },
                    text = optionText,
                    isSelected = isSelected,
                    isSubmitted = uiState.isCurrentAnswerSubmitted,
                    isCorrect = isCorrect,
                    onClick = {
                        if (!uiState.isCurrentAnswerSubmitted) {
                            onSelectOption(index)
                        }
                    },
                    tag = "quiz_option_${index}"
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            // --- Feedback / Explanation Card (After Submit) ---
            if (uiState.isCurrentAnswerSubmitted) {
                item {
                    val wasCorrect = selectedOption == currentQuestion.correctOptionIndex
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (wasCorrect) MintUltraLight else Color(0xFFFFF1F2)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .border(
                                1.dp,
                                if (wasCorrect) Color(0xFF86EFAC) else Color(0xFFFECDD3),
                                RoundedCornerShape(18.dp)
                            )
                            .testTag("quiz_feedback_card")
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (wasCorrect) Icons.Default.CheckCircle else Icons.Default.Info,
                                    contentDescription = null,
                                    tint = if (wasCorrect) Color(0xFF15803D) else Color(0xFFBE123C),
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (wasCorrect) "Jawaban Benar! 🎉" else "Jawaban Kurang Tepat",
                                    fontWeight = FontWeight.Bold,
                                    color = if (wasCorrect) Color(0xFF15803D) else Color(0xFFBE123C),
                                    fontSize = 14.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = currentQuestion.explanation,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextPrimary,
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp
                                )
                            )
                        }
                    }
                }
            }

            // --- Action Buttons ---
            item {
                Spacer(modifier = Modifier.height(10.dp))
                if (!uiState.isCurrentAnswerSubmitted) {
                    Button(
                        onClick = onSubmitAnswer,
                        enabled = selectedOption != null,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TealPrimary,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("btn_submit_quiz")
                    ) {
                        Text(
                            text = "Kirim Jawaban",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                } else {
                    Button(
                        onClick = onNextQuestion,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TealPrimary,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("btn_next_quiz")
                    ) {
                        Text(
                            text = if (uiState.currentQuizIndex + 1 < total) "Pertanyaan Berikutnya" else "Lihat Hasil Akhir",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuizOptionCard(
    optionLetter: String,
    text: String,
    isSelected: Boolean,
    isSubmitted: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit,
    tag: String,
    modifier: Modifier = Modifier
) {
    val borderColor = when {
        isSubmitted && isCorrect -> Color(0xFF22C55E)
        isSubmitted && isSelected && !isCorrect -> Color(0xFFEF4444)
        isSelected -> TealPrimary
        else -> BorderSoft
    }

    val containerColor = when {
        isSubmitted && isCorrect -> Color(0xFFF0FDF4)
        isSubmitted && isSelected && !isCorrect -> Color(0xFFFEF2F2)
        isSelected -> MintSoft
        else -> MaterialTheme.colorScheme.surface
    }

    val letterBg = when {
        isSubmitted && isCorrect -> Color(0xFF22C55E)
        isSubmitted && isSelected && !isCorrect -> Color(0xFFEF4444)
        isSelected -> TealPrimary
        else -> Color(0xFFF1F5F9)
    }

    val letterColor = when {
        isSelected || (isSubmitted && isCorrect) -> Color.White
        else -> TextPrimary
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = containerColor,
        shadowElevation = if (isSelected) 2.dp else 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag(tag)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(letterBg),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = optionLetter,
                    color = letterColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextPrimary,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                ),
                modifier = Modifier.weight(1f)
            )

            if (isSubmitted) {
                if (isCorrect) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Benar",
                        tint = Color(0xFF16A34A),
                        modifier = Modifier.size(20.dp)
                    )
                } else if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Salah",
                        tint = Color(0xFFDC2626),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun QuizResultView(
    score: Int,
    totalQuestions: Int,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val motivationalTitle = when {
        score >= 80 -> "Luar Biasa! Ahli Senyum Sehat! 🌟"
        score >= 60 -> "Bagus Sekali! Pengetahuan Anda Cukup Baik 👍"
        else -> "Tetap Semangat! Terus Belajar Demi Gigi Sehat 💪"
    }

    val motivationalDesc = when {
        score >= 80 -> "Pemahaman Anda tentang perawatan gigi dan mulut sangat matang. Terus pertahankan kebiasaan baik ini setiap hari bersama keluarga!"
        score >= 60 -> "Anda sudah memahami prinsip-prinsip penting kesehatan gigi. Tingkatkan lagi pemahaman Anda dengan membaca ulang materi."
        else -> "Jangan khawatir! Kesehatan gigi adalah proses belajar sehari-hari. Baca materi edukasi yang tersedia dan coba kuis ini kembali."
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("quiz_result_view"),
        contentPadding = PaddingValues(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(MintSoft),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = TealPrimary,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Hasil Evaluasi Kuis",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "$score / 100",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = TealPrimaryDark,
                    fontSize = 42.sp
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MintUltraLight),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFD1FAE5), RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = motivationalTitle,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TealPrimaryDark,
                            fontSize = 16.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = motivationalDesc,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextPrimary,
                            fontSize = 13.sp,
                            lineHeight = 20.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onRestart,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TealPrimary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("btn_restart_quiz")
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Coba Kuis Lagi",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}
