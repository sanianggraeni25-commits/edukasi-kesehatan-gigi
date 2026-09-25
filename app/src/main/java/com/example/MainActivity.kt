package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.AppScreen
import com.example.ui.components.DentalTopAppBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.BrushingGuideScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.TipsScreen
import com.example.ui.screens.TopicDetailScreen
import com.example.ui.screens.TopicsScreen
import com.example.ui.theme.MintSoft
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealPrimaryDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.DentalViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: DentalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                DentalApp(viewModel = viewModel)
            }
        }
    }
}

data class NavItem(
    val screen: AppScreen,
    val label: String,
    val icon: ImageVector,
    val testTag: String
)

@Composable
fun DentalApp(viewModel: DentalViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = uiState.currentScreen != AppScreen.HOME) {
        viewModel.navigateBack()
    }

    val navItems = listOf(
        NavItem(AppScreen.HOME, "Beranda", Icons.Default.Home, "nav_item_home"),
        NavItem(AppScreen.TOPICS, "Materi", Icons.Default.MenuBook, "nav_item_topics"),
        NavItem(AppScreen.BRUSHING_GUIDE, "Sikat Gigi", Icons.Default.CleaningServices, "nav_item_brushing"),
        NavItem(AppScreen.TIPS, "Tips", Icons.Default.Lightbulb, "nav_item_tips"),
        NavItem(AppScreen.QUIZ, "Kuis", Icons.Default.HelpOutline, "nav_item_quiz")
    )

    val currentTitle = when (uiState.currentScreen) {
        AppScreen.HOME -> "GigiSehat"
        AppScreen.TOPICS -> "Materi Edukasi"
        AppScreen.TOPIC_DETAIL -> uiState.selectedTopic?.title ?: "Detail Materi"
        AppScreen.BRUSHING_GUIDE -> "Panduan Sikat Gigi"
        AppScreen.TIPS -> "Tips Kesehatan Gigi"
        AppScreen.QUIZ -> "Kuis Kesehatan Gigi"
        AppScreen.ABOUT -> "Tentang GigiSehat"
    }

    val currentSubtitle = when (uiState.currentScreen) {
        AppScreen.HOME -> "Edukasi Gigi & Mulut Semua Usia"
        AppScreen.TOPICS -> "Penyebab, Gejala, & Pencegahan"
        AppScreen.TOPIC_DETAIL -> uiState.selectedTopic?.category
        AppScreen.BRUSHING_GUIDE -> "Teknik 45° & Timer 2 Menit"
        AppScreen.TIPS -> "8 Kebiasaan Sehat Terbukti"
        AppScreen.QUIZ -> "Uji Wawasan Anda"
        AppScreen.ABOUT -> "Media Edukasi Masyarakat"
    }

    val showBackButton = uiState.currentScreen == AppScreen.TOPIC_DETAIL ||
            uiState.currentScreen == AppScreen.ABOUT

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth >= 600.dp

        Scaffold(
            topBar = {
                DentalTopAppBar(
                    title = currentTitle,
                    subtitle = currentSubtitle,
                    showBackButton = showBackButton,
                    onBackClick = { viewModel.navigateBack() }
                )
            },
            bottomBar = {
                if (!isWideScreen) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 4.dp
                    ) {
                        navItems.forEach { item ->
                            val selected = uiState.currentScreen == item.screen ||
                                    (item.screen == AppScreen.TOPICS && uiState.currentScreen == AppScreen.TOPIC_DETAIL)

                            NavigationBarItem(
                                selected = selected,
                                onClick = { viewModel.navigateTo(item.screen) },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.label
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.label,
                                        fontSize = 11.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = TealPrimaryDark,
                                    selectedTextColor = TealPrimaryDark,
                                    indicatorColor = MintSoft,
                                    unselectedIconColor = TextSecondary,
                                    unselectedTextColor = TextSecondary
                                ),
                                modifier = Modifier.testTag(item.testTag)
                            )
                        }

                        // Info / Tentang icon in bottom bar
                        val aboutSelected = uiState.currentScreen == AppScreen.ABOUT
                        NavigationBarItem(
                            selected = aboutSelected,
                            onClick = { viewModel.navigateTo(AppScreen.ABOUT) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Tentang"
                                )
                            },
                            label = {
                                Text(
                                    text = "Tentang",
                                    fontSize = 11.sp
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = TealPrimaryDark,
                                selectedTextColor = TealPrimaryDark,
                                indicatorColor = MintSoft,
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                            ),
                            modifier = Modifier.testTag("nav_item_about")
                        )
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (isWideScreen) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        navItems.forEach { item ->
                            val selected = uiState.currentScreen == item.screen ||
                                    (item.screen == AppScreen.TOPICS && uiState.currentScreen == AppScreen.TOPIC_DETAIL)

                            NavigationRailItem(
                                selected = selected,
                                onClick = { viewModel.navigateTo(item.screen) },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.label
                                    )
                                },
                                label = { Text(text = item.label) },
                                colors = NavigationRailItemDefaults.colors(
                                    selectedIconColor = TealPrimaryDark,
                                    selectedTextColor = TealPrimaryDark,
                                    indicatorColor = MintSoft,
                                    unselectedIconColor = TextSecondary,
                                    unselectedTextColor = TextSecondary
                                ),
                                modifier = Modifier.testTag("${item.testTag}_rail")
                            )
                        }

                        val aboutSelected = uiState.currentScreen == AppScreen.ABOUT
                        NavigationRailItem(
                            selected = aboutSelected,
                            onClick = { viewModel.navigateTo(AppScreen.ABOUT) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Tentang"
                                )
                            },
                            label = { Text(text = "Tentang") },
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = TealPrimaryDark,
                                selectedTextColor = TealPrimaryDark,
                                indicatorColor = MintSoft,
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                            ),
                            modifier = Modifier.testTag("nav_item_about_rail")
                        )
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    Crossfade(
                        targetState = uiState.currentScreen,
                        label = "screen_crossfade"
                    ) { screen ->
                        when (screen) {
                            AppScreen.HOME -> HomeScreen(
                                uiState = uiState,
                                onNavigate = { viewModel.navigateTo(it) },
                                onTopicClick = { viewModel.openTopicDetail(it) },
                                onRefreshFact = { viewModel.refreshFact() }
                            )

                            AppScreen.TOPICS -> TopicsScreen(
                                uiState = uiState,
                                onTopicClick = { viewModel.openTopicDetail(it) },
                                onSearchChange = { viewModel.setTopicSearchQuery(it) },
                                onCategoryChange = { viewModel.setTopicCategory(it) }
                            )

                            AppScreen.TOPIC_DETAIL -> TopicDetailScreen(
                                topic = uiState.selectedTopic,
                                onBack = { viewModel.navigateBack() }
                            )

                            AppScreen.BRUSHING_GUIDE -> BrushingGuideScreen(
                                uiState = uiState,
                                onToggleTimer = { viewModel.toggleTimer() },
                                onResetTimer = { viewModel.resetTimer() }
                            )

                            AppScreen.TIPS -> TipsScreen(
                                uiState = uiState,
                                onCategoryChange = { viewModel.setTipCategory(it) },
                                onToggleBookmark = { viewModel.toggleTipBookmark(it) }
                            )

                            AppScreen.QUIZ -> QuizScreen(
                                uiState = uiState,
                                onSelectOption = { viewModel.selectQuizAnswer(it) },
                                onSubmitAnswer = { viewModel.submitQuizAnswer() },
                                onNextQuestion = { viewModel.nextQuizQuestion() },
                                onRestartQuiz = { viewModel.restartQuiz() }
                            )

                            AppScreen.ABOUT -> AboutScreen()
                        }
                    }
                }
            }
        }
    }
}
