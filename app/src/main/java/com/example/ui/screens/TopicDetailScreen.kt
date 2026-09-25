package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.DentalTopic
import com.example.ui.components.InfoBulletItem
import com.example.ui.theme.BorderSoft
import com.example.ui.theme.MintAccent
import com.example.ui.theme.MintSoft
import com.example.ui.theme.MintUltraLight
import com.example.ui.theme.SkyDeep
import com.example.ui.theme.SkySoft
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealPrimaryDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun TopicDetailScreen(
    topic: DentalTopic?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBack()
    }

    if (topic == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Materi tidak ditemukan.", color = TextSecondary)
        }
        return
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("topic_detail_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // --- 1. HERO 3D IMAGE & TITLE ---
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
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
                            text = topic.title,
                            fontWeight = FontWeight.Bold,
                            color = TealPrimary
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.95f),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MintSoft,
                            modifier = Modifier.size(8.dp)
                        ) {}
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = topic.category,
                            color = TealPrimaryDark,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        fontSize = 22.sp
                    )
                )
            }
        }

        // --- 2. PENGERTIAN SINGKAT CARD ---
        item {
            DetailSectionCard(
                title = "Pengertian Singkat",
                icon = Icons.Default.Info,
                iconTint = TealPrimary,
                iconBg = MintSoft,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = topic.overview,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextPrimary,
                        fontSize = 14.sp,
                        lineHeight = 22.sp
                    )
                )
            }
        }

        // --- 3. PENYEBAB ATAU FAKTOR RISIKO CARD ---
        if (topic.causes.isNotEmpty()) {
            item {
                DetailSectionCard(
                    title = "Penyebab & Faktor Risiko",
                    icon = Icons.Default.WarningAmber,
                    iconTint = Color(0xFFD97706),
                    iconBg = Color(0xFFFEF3C7),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    topic.causes.forEach { cause ->
                        InfoBulletItem(
                            text = cause,
                            icon = Icons.Default.Block,
                            iconColor = Color(0xFFD97706)
                        )
                    }
                }
            }
        }

        // --- 4. TANDA DAN GEJALA CARD ---
        if (topic.symptoms.isNotEmpty()) {
            item {
                DetailSectionCard(
                    title = "Tanda & Gejala yang Perlu Diwaspadai",
                    icon = Icons.Default.Healing,
                    iconTint = Color(0xFFE11D48),
                    iconBg = Color(0xFFFFE4E6),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    topic.symptoms.forEach { symptom ->
                        InfoBulletItem(
                            text = symptom,
                            icon = Icons.Default.WarningAmber,
                            iconColor = Color(0xFFE11D48)
                        )
                    }
                }
            }
        }

        // --- 5. CARA MENCEGAH CARD ---
        if (topic.prevention.isNotEmpty()) {
            item {
                DetailSectionCard(
                    title = "Cara Mencegah Sejak Dini",
                    icon = Icons.Default.Shield,
                    iconTint = Color(0xFF16A34A),
                    iconBg = Color(0xFFDCFCE7),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    topic.prevention.forEach { preventItem ->
                        InfoBulletItem(
                            text = preventItem,
                            icon = Icons.Default.CheckCircle,
                            iconColor = Color(0xFF16A34A)
                        )
                    }
                }
            }
        }

        // --- 6. CARA MERAWAT CARD ---
        if (topic.care.isNotEmpty()) {
            item {
                DetailSectionCard(
                    title = "Cara Merawat & Penanganan",
                    icon = Icons.Default.Healing,
                    iconTint = SkyDeep,
                    iconBg = SkySoft,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    topic.care.forEach { careItem ->
                        InfoBulletItem(
                            text = careItem,
                            icon = Icons.Default.CheckCircle,
                            iconColor = SkyDeep
                        )
                    }
                }
            }
        }

        // --- 7. KAPAN HARUS KE DOKTER GIGI CARD ---
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(20.dp))
                    .testTag("when_to_dentist_card")
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDBEAFE)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalHospital,
                            contentDescription = null,
                            tint = Color(0xFF1D4ED8),
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Kapan Harus ke Dokter Gigi?",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E3A8A),
                                fontSize = 15.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = topic.whenToDentist,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFF1E293B),
                                fontSize = 13.sp,
                                lineHeight = 20.sp
                            )
                        )
                    }
                }
            }
        }

        // --- 8. TAHUKAH ANDA / FUN FACT ---
        if (topic.funFact.isNotBlank()) {
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MintUltraLight),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(20.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = TealPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Tahukah Anda?",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TealPrimaryDark,
                                    fontSize = 12.sp
                                )
                            )
                            Text(
                                text = topic.funFact,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextPrimary,
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailSectionCard(
    title: String,
    icon: ImageVector,
    iconTint: Color,
    iconBg: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, BorderSoft, RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        fontSize = 15.sp
                    )
                )
            }

            content()
        }
    }
}
