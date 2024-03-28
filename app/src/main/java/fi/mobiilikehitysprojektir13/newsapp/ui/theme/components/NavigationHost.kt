package fi.mobiilikehitysprojektir13.newsapp.ui.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fi.mobiilikehitysprojektir13.newsapp.Screens
import fi.mobiilikehitysprojektir13.newsapp.screens.article.ArticleScreen
import fi.mobiilikehitysprojektir13.newsapp.screens.news.NewsScreen
import fi.mobiilikehitysprojektir13.newsapp.screens.settings.SettingsScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screens.News.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(Screens.News.route) {
            NewsScreen(navController)
        }
        composable(
            Screens.Article.route,
            arguments = listOf(navArgument(Screens.Article.argument) { type = NavType.StringType })
        ) {
            ArticleScreen(navController, it)
        }
        composable(Screens.Settings.route) {
            SettingsScreen()
        }
        composable(Screens.Favorite.route) {
            Text(text = "Favorites? :)")
        }
    }
}