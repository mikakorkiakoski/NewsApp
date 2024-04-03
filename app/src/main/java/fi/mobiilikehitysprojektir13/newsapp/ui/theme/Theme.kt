package fi.mobiilikehitysprojektir13.newsapp.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import fi.mobiilikehitysprojektir13.newsapp.data.store.UserSettingsStore
import fi.mobiilikehitysprojektir13.newsapp.screens.settings.FontSize

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF64B5F6),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF64B5F6),
    onPrimaryContainer = Color.Black,
    inversePrimary = Color.Black,
    secondary = Color(0xFF64B5F6),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFBBDEFB),
    onSecondaryContainer = Color.Black,
    tertiary = Color.Black,
    onTertiary = Color.White,
    tertiaryContainer = Color.Black,
    onTertiaryContainer = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFF5F5F5),
    onSurface = Color.Black,
    surfaceVariant = Color(0xFFFAFAFA),
    onSurfaceVariant = Color.Black,
    surfaceTint = Color(0xFFE0E0E0),
    inverseSurface = Color.Black,
    inverseOnSurface = Color.White,
    error = Color.Red,
    onError = Color.White,
    errorContainer = Color.Red,
    onErrorContainer = Color.White,
    outline = Color.Gray,
    outlineVariant = Color.LightGray,
    scrim = Color.Black.copy(alpha = 0.4f),
    surfaceBright = Color.White,
    surfaceDim = Color(0xFFE0E0E0),
    surfaceContainer = Color(0xFFE0E0E0),
    surfaceContainerHigh = Color(0xFFD1D1D1),
    surfaceContainerHighest = Color(0xFFBDBDBD),
    surfaceContainerLow = Color(0xFFEEEEEE),
    surfaceContainerLowest = Color(0xFFFAFAFA)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF2196F3),
    onPrimaryContainer = Color.White,
    inversePrimary = Color.White,
    secondary = Color(0xFF0D47A1),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF1565C0),
    onSecondaryContainer = Color.White,
    tertiary = Color.White,
    onTertiary = Color.Black,
    tertiaryContainer = Color.White,
    onTertiaryContainer = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF212121),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF303030),
    onSurfaceVariant = Color.White,
    surfaceTint = Color(0xFFFAFAFA),
    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,
    error = Color.Red,
    onError = Color.White,
    errorContainer = Color.Red,
    onErrorContainer = Color.White,
    outline = Color.Gray,
    outlineVariant = Color.LightGray,
    scrim = Color.Black.copy(alpha = 0.4f),
    surfaceBright = Color.White,
    surfaceDim = Color(0xFFE0E0E0),
    surfaceContainer = Color(0xFFE0E0E0),
    surfaceContainerHigh = Color(0xFFD1D1D1),
    surfaceContainerHighest = Color(0xFFBDBDBD),
    surfaceContainerLow = Color(0xFFEEEEEE),
    surfaceContainerLowest = Color(0xFFFAFAFA)
)

@Composable
fun NewsTheme(
    content: @Composable () -> Unit
) {

    val context = LocalContext.current
    val dataStore = UserSettingsStore(context)

    val fontSize by dataStore.getFontSize.collectAsState(FontSize.Medium)
    val isDarkMode by dataStore.isDarkMode.collectAsState(true)

    val colorScheme = when {
        isDarkMode -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = colorScheme.background.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkMode
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = Typography(fontSize), content = content
    )
}