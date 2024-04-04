package fi.mobiilikehitysprojektir13.newsapp.data.api.news

import fi.mobiilikehitysprojektir13.newsapp.data.dto.News

interface NewsDataApi {
    suspend fun getLatestNews(
        query: String = "",
        categories: Set<String> = emptySet(),
        countries: Set<String> = emptySet(),
        languages: Set<String> = emptySet()
    ): News
}