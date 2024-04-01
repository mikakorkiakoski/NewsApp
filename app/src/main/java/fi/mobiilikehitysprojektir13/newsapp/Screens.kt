package fi.mobiilikehitysprojektir13.newsapp

sealed class  Screens(val route: String, val argument: String = "") {
    data object News : Screens("news")
    data object Article : Screens("article/{articleId}", "articleId")
    data object Favorite : Screens("favorite")
    data object Settings : Screens("settings")
}