package fi.mobiilikehitysprojektir13.newsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import fi.mobiilikehitysprojektir13.newsapp.screens.settings.FontSize

fun Typography(fontSize: FontSize): Typography {
    val spFontSize = when (fontSize) {
        FontSize.Small -> 12.sp
        FontSize.Medium -> 16.sp
        FontSize.Large -> 20.sp
        FontSize.ExtraLarge -> 24.sp
    }

    return Typography(
        displayLarge = TextStyle(fontSize = spFontSize),
        displayMedium = TextStyle(fontSize = spFontSize),
        displaySmall = TextStyle(fontSize = spFontSize),
        bodyLarge = TextStyle(fontSize = spFontSize),
        bodyMedium = TextStyle(fontSize = spFontSize),
        bodySmall = TextStyle(fontSize = spFontSize),
    )
}