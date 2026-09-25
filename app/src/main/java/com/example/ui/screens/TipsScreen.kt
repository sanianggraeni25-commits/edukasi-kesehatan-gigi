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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.filled.Sanitizer
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DentalDataSource
import com.example.model.DentalTip
import com.example.ui.components.SoftCategoryChip
import com.example.ui.theme.BorderSoft
import com.example.ui.theme.MintSoft
import com.example.ui.theme.MintUltraLight
import com.example.ui.theme.SkyDeep
import com.example.ui.theme.SkySoft
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealPrimaryDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.DentalUiState

@Composable
fun TipsScreen(
    uiState: DentalUiState,
    onCategoryChange: (String) -> Unit,
    onToggleBookmark: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = remember {
        listOf("Semua", "Harian", "Alat", "Nutrisi", "Pencegahan")
    }

    val filteredTips = remember(uiState.selectedTipCategory) {
        if (uiState.selectedTipCategory == "Semua") {
            DentalDataSource.dentalTips
        } else {
            DentalDataSource.dentalTips.filter {
                it.category.equals(uiState.selectedTipCategory, ignoreCase = true)
            }
        }
    }

    val appliedCount = DentalDataSource.dentalTips.count { uiState.bookmarkedTipIds.contains(it.id) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("tips_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // --- 1. HEADER SUMMARY CARD ---
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MintUltraLight),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .border(1.dp, Color(0xFFD1FAE5), RoundedCornerShape(20.dp))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = TealPrimary,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Kebiasaan Sehat Saya",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TealPrimaryDark,
                                fontSize = 15.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "$appliedCount dari ${DentalDataSource.dentalTips.size} kebiasaan sudah Anda terapkan. Ketuk tanda centang pada kartu untuk melacak kebiasaan Anda.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        )
                    }
                }
            }
        }

        // --- 2. CATEGORY CHIPS ---
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    SoftCategoryChip(
                        text = category,
                        isSelected = uiState.selectedTipCategory == category,
                        onClick = { onCategoryChange(category) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // --- 3. TIPS CARDS ---
        items(filteredTips, key = { it.id }) { tip ->
            val isApplied = uiState.bookmarkedTipIds.contains(tip.id)
            DentalTipCard(
                tip = tip,
                isApplied = isApplied,
                onToggleApplied = { onToggleBookmark(tip.id) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }
    }
}

@Composable
fun DentalTipCard(
    tip: DentalTip,
    isApplied: Boolean,
    onToggleApplied: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tipIcon: ImageVector = when (tip.id) {
        "tip_1" -> Icons.Default.Schedule
        "tip_2" -> Icons.Default.Sanitizer
        "tip_3" -> Icons.Default.MedicalServices
        "tip_4" -> Icons.Default.NoFood
        "tip_5" -> Icons.Default.LocalDrink
        "tip_6" -> Icons.Default.MedicalServices
        "tip_7" -> Icons.Default.Timer
        else -> Icons.Default.Lightbulb
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp,
                if (isApplied) TealPrimary.copy(alpha = 0.5f) else BorderSoft,
                RoundedCornerShape(20.dp)
            )
            .testTag("tip_card_${tip.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isApplied) MintSoft else Color(0xFFF1F5F9)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = tipIcon,
                            contentDescription = null,
                            tint = if (isApplied) TealPrimary else TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isApplied) MintSoft else Color(0xFFF1F5F9),
                            modifier = Modifier.padding(bottom = 2.dp)
                        ) {
                            Text(
                                text = tip.category,
                                color = if (isApplied) TealPrimaryDark else TextSecondary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        Text(
                            text = tip.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                fontSize = 15.sp
                            )
                        )
                    }
                }

                IconButton(
                    onClick = onToggleApplied,
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("toggle_tip_${tip.id}")
                ) {
                    Icon(
                        imageVector = if (isApplied) Icons.Default.CheckCircle else Icons.Default.BookmarkBorder,
                        contentDescription = if (isApplied) "Telah Diterapkan" else "Tandai Diterapkan",
                        tint = if (isApplied) TealPrimary else TextSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = tip.summary,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = tip.detail,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MintUltraLight,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = TealPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = tip.highlight,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = TealPrimaryDark,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }
    }
}
