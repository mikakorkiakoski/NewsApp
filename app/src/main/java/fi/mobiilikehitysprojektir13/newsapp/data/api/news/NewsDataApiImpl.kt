package fi.mobiilikehitysprojektir13.newsapp.data.api.news

import fi.mobiilikehitysprojektir13.newsapp.data.dto.News
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.request.parameter

class NewsDataApiImpl(private val httpClient: HttpClient) : NewsDataApi {

    companion object {
        // WARNING Put key here but DON'T commit it
        private const val NEWS_API_KEY = "pub_400748a7dd522f1ae9d4c6725d3062c3a70e2"
    }

    override suspend fun getLatestNews(
        query: String,
        categories: Set<String>,
        countries: Set<String>,
        languages: Set<String>,
        newsPage: String
    ): News = httpClient.get("news") {
        host = "newsdata.io/api/1"
        parameter("apikey", NEWS_API_KEY)
        if (query.trim().isNotBlank()) parameter("q", query)
        if (categories.isNotEmpty()) parameter("category", categories.joinToString(","))
        if (countries.isNotEmpty()) parameter("country", countries.joinToString(","))
        if (languages.isNotEmpty()) parameter("language", languages.joinToString(","))
        if (newsPage.isNotBlank()) parameter("page", newsPage)
    }.body<News>()
}