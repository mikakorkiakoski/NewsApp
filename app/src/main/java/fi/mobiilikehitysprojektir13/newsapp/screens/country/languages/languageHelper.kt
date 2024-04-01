package fi.mobiilikehitysprojektir13.newsapp.screens.country.languages

fun getLanguageCode(languageName: String): String? {
    val language = languages.find { it.name == languageName }
    return language?.code
}
