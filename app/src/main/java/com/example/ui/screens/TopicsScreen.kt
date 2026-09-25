package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DentalDataSource
import com.example.model.DentalTopic
import com.example.ui.components.SoftCategoryChip
import com.example.ui.theme.BorderSoft
import com.example.ui.theme.MintSoft
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealPrimaryDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.DentalUiState

@Composable
fun TopicsScreen(
    uiState: DentalUiState,
    onTopicClick: (DentalTopic) -> Unit,
    onSearchChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = remember {
        listOf(
            "Semua",
            "Dasar Anatomi",
            "Kebersihan Harian",
            "Penyakit Gigi",
            "Kebersihan Mulut",
            "Kesehatan Jaringan Lunak",
            "Gizi & Nutrisi",
            "Perawatan Komprehensif",
            "Pencegahan & Medis"
        )
    }

    val filteredTopics = remember(uiState.topicSearchQuery, uiState.selectedTopicCategory) {
        DentalDataSource.topics.filter { topic ->
            val matchQuery = if (uiState.topicSearchQuery.isBlank()) true else {
                topic.title.contains(uiState.topicSearchQuery, ignoreCase = true) ||
                        topic.shortDescription.contains(uiState.topicSearchQuery, ignoreCase = true) ||
                        topic.category.contains(uiState.topicSearchQuery, ignoreCase = true)
            }
            val matchCategory = if (uiState.selectedTopicCategory == "Semua") true else {
                topic.category.equals(uiState.selectedTopicCategory, ignoreCase = true)
            }
            matchQuery && matchCategory
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("topics_screen"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // --- Search Bar ---
        item {
            OutlinedTextField(
                value = uiState.topicSearchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("search_topic_input"),
                placeholder = {
                    Text(
                        "Cari materi (contoh: karies, sikat gigi, karang...)",
                        color = TextSecondary,
                        fontSize = 13.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Cari",
                        tint = TealPrimary
                    )
                },
                trailingIcon = {
                    if (uiState.topicSearchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Hapus pencarian",
                                tint = TextSecondary
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TealPrimary,
                    unfocusedBorderColor = BorderSoft,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        }

        // --- Category Filter Chips ---
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    SoftCategoryChip(
                        text = category,
                        isSelected = uiState.selectedTopicCategory == category,
                        onClick = { onCategoryChange(category) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }

        // --- Result count ---
        item {
            Text(
                text = "Menampilkan ${filteredTopics.size} materi edukasi",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = TextSecondary,
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

        // --- Topics List Cards ---
        if (filteredTopics.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tidak ada materi yang sesuai dengan pencarian Anda.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextSecondary,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        } else {
            items(filteredTopics, key = { it.id }) { topic ->
                TopicListCard(
                    topic = topic,
                    onClick = { onTopicClick(topic) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
fun TopicListCard(
    topic: DentalTopic,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, BorderSoft, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .testTag("topic_card_${topic.id}")
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(86.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                if (topic.drawableResId != null) {
                    Image(
                        painter = painterResource(id = topic.drawableResId),
                        contentDescription = topic.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MintSoft),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = topic.badgeText,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = TealPrimaryDark
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MintSoft,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = topic.category,
                        color = TealPrimaryDark,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        fontSize = 15.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = topic.shortDescription,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pelajari Materi",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TealPrimary,
                            fontSize = 12.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = TealPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}
