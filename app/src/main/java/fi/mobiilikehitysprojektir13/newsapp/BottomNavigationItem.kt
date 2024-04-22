package fi.mobiilikehitysprojektir13.newsapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    @Composable
    fun bottomNavigationItems(): List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            label = "News",
            icon = ImageVector.vectorResource(R.drawable.baseline_newspaper_24),
            route = Screens.News.route
        ),
        BottomNavigationItem(
            label = "Favorites",
            icon = Icons.Filled.Star,
            route = Screens.Favorites.route
        ),
        BottomNavigationItem(
            label = "Settings",
            icon = Icons.Filled.Settings,
            route = Screens.Settings.route
        ),
    )
}