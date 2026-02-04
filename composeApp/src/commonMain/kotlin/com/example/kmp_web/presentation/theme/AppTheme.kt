package com.example.kmp_web.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kmp_web.composeapp.generated.resources.Cairo_Bold
import kmp_web.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC5),
    tertiary = Color(0xFF3700B3)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC5),
    tertiary = Color(0xFF3700B3)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Load the font from commonMain/composeResources/font/notosans_arabic.ttf
    val arabicFont = FontFamily(
        Font(Res.font.Cairo_Bold, FontWeight.Normal)
    )

    // Apply the font to your typography styles
    val appTypography = Typography(
        bodyLarge = TextStyle(fontFamily = arabicFont, fontSize = 16.sp),
        bodyMedium = TextStyle(fontFamily = arabicFont, fontSize = 14.sp),
        labelLarge = TextStyle(fontFamily = arabicFont, fontSize = 14.sp),
        titleLarge = TextStyle(fontFamily = arabicFont, fontSize = 22.sp)
    )

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography,
        content = content
    )
}