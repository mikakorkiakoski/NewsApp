package fi.mobiilikehitysprojektir13.newsapp

sealed class  Screens(val route: String, val argument: String = "") {
    data object News : Screens("news")
    data object Article : Screens("article/{articleId}", "articleId")
}