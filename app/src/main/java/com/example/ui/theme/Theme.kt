package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DentalLightColorScheme = lightColorScheme(
    primary = TealPrimary,
    onPrimary = Color.White,
    primaryContainer = MintSoft,
    onPrimaryContainer = TealPrimaryDark,
    secondary = SkyDeep,
    onSecondary = Color.White,
    secondaryContainer = SkySoft,
    onSecondaryContainer = Color(0xFF01579B),
    tertiary = MintAccent,
    onTertiary = Color.White,
    tertiaryContainer = MintUltraLight,
    onTertiaryContainer = Color(0xFF00382E),
    background = BackgroundLight,
    onBackground = TextPrimary,
    surface = SurfaceCard,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceCardAlt,
    onSurfaceVariant = TextSecondary,
    outline = BorderSoft,
    outlineVariant = Color(0xFFE2E8F0)
)

private val DentalDarkColorScheme = darkColorScheme(
    primary = TealLight,
    onPrimary = Color(0xFF00382F),
    primaryContainer = Color(0xFF005048),
    onPrimaryContainer = Color(0xFFA3F2E4),
    secondary = Color(0xFF81D4FA),
    onSecondary = Color(0xFF00344F),
    secondaryContainer = Color(0xFF004C72),
    onSecondaryContainer = Color(0xFFC7E7FF),
    background = Color(0xFF111E1D),
    onBackground = Color(0xFFE0EAE8),
    surface = Color(0xFF162524),
    onSurface = Color(0xFFE0EAE8),
    surfaceVariant = Color(0xFF1E3230),
    onSurfaceVariant = Color(0xFFB0C4C1),
    outline = Color(0xFF334B49)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use our handcrafted soft medical theme by default
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DentalDarkColorScheme
        else -> DentalLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
