package fi.mobiilikehitysprojektir13.newsapp.data.api

import fi.mobiilikehitysprojektir13.newsapp.data.dto.News
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

class NewsDataApiImpl(private val httpClient: HttpClient) : NewsDataApi {
    override suspend fun getLatestNews(): News = httpClient.get("news").body()
}