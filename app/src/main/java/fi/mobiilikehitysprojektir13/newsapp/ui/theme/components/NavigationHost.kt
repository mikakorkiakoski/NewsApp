package fi.mobiilikehitysprojektir13.newsapp.ui.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fi.mobiilikehitysprojektir13.newsapp.Screens
import fi.mobiilikehitysprojektir13.newsapp.data.store.UserSettingsStore
import fi.mobiilikehitysprojektir13.newsapp.screens.article.ArticleScreen
import fi.mobiilikehitysprojektir13.newsapp.screens.country.CountrySelectionScreen
import fi.mobiilikehitysprojektir13.newsapp.screens.news.NewsScreen
import fi.mobiilikehitysprojektir13.newsapp.screens.settings.SettingsScreen

@Composable
fun NavigationHost(
    navController: NavHostController, paddingValues: PaddingValues
) {

    val context = LocalContext.current
    val userSettingsStore = UserSettingsStore(context)
    val country = userSettingsStore.getCountry.collectAsState(initial = "")

    NavHost(
        navController = navController, startDestination = when {
            country.value.isNotBlank() -> Screens.News.route
            else -> Screens.Map.route
        }, modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(Screens.Map.route) {
            CountrySelectionScreen(navController)
        }
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
            SettingsScreen(navController)
        }
        composable(Screens.Favorite.route) {
            Text(text = "Favorites? :)")
        }
    }
}