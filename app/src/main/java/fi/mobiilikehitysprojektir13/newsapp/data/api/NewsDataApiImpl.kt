package fi.mobiilikehitysprojektir13.newsapp.data.api

import android.util.Log
import fi.mobiilikehitysprojektir13.newsapp.data.dto.News
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.request

class NewsDataApiImpl(private val httpClient: HttpClient) : NewsDataApi {
    override suspend fun getLatestNews(
        query: String, categories: Set<String>, countries: Set<String>, languages: Set<String>
    ): News {

        Log.e("getLatestNews: ", "$query | $categories")

        val request = httpClient.get("news") {
            if (query.trim().isNotBlank()) parameter("q", query)
            if (categories.isNotEmpty()) parameter("category", categories.joinToString(","))
            if (countries.isNotEmpty()) parameter("country", countries.joinToString(","))
            if (languages.isNotEmpty()) parameter("language", languages.joinToString(","))
        }

        Log.d("getLatestNews: ", request.request.url.toString())

        return request.body<News>()
    }
}