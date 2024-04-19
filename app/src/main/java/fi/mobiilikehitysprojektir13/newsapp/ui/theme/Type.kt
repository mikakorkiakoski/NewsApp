package fi.mobiilikehitysprojektir13.newsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fi.mobiilikehitysprojektir13.newsapp.screens.settings.FontSize

fun Typography(fontSize: FontSize): Typography {
    val fontScale = when (fontSize) {
        FontSize.Small -> -2
        FontSize.Medium -> 0
        FontSize.Large -> 2
        FontSize.ExtraLarge -> 4
    }

    return Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = (16 + fontScale).sp,
            lineHeight = ((16 + fontScale) * 1.15).sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = (11 + fontScale).sp,
            lineHeight = ((11 + fontScale) * 1.15).sp,
            letterSpacing = 0.5.sp
        ),
    )
}