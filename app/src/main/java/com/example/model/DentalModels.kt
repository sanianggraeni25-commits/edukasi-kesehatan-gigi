package com.example.model

import androidx.annotation.DrawableRes

data class DentalTopic(
    val id: String,
    val title: String,
    val category: String,
    val shortDescription: String,
    @DrawableRes val drawableResId: Int?,
    val badgeText: String,
    val overview: String,
    val causes: List<String>,
    val symptoms: List<String>,
    val prevention: List<String>,
    val care: List<String>,
    val whenToDentist: String,
    val funFact: String = ""
)

data class BrushingStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val proTip: String,
    val targetArea: String,
    val durationSeconds: Int = 24
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String
)

data class DentalTip(
    val id: String,
    val title: String,
    val summary: String,
    val category: String,
    val detail: String,
    val highlight: String
)

enum class AppScreen {
    HOME,
    TOPICS,
    TOPIC_DETAIL,
    BRUSHING_GUIDE,
    TIPS,
    QUIZ,
    ABOUT
}
