package fi.mobiilikehitysprojektir13.newsapp.di

import fi.mobiilikehitysprojektir13.newsapp.data.api.NewsDataApi
import fi.mobiilikehitysprojektir13.newsapp.data.api.NewsDataApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

// WARNING Put key here but DON'T commit it
private const val API_KEY = "pub_40074d1d7872307fc1b23af0339c4f83e5401"

// TODO Replace with real data from other screen, as example over Pref Settings
private val categories = emptySet<String>()
private val countries = emptySet<String>()
private val languages = emptySet<String>()

val networkModule = module {
    single { provideKtorClient() }
    single { provideNashStoreApiService(get()) }
}

private fun provideKtorClient(): HttpClient = HttpClient(Android) {
    expectSuccess = true
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
    install(HttpCache)
    defaultRequest {
        url {
            host = "newsdata.io/api/1"
            protocol = URLProtocol.HTTPS
            contentType(ContentType.Application.Json)
            parameters.append("apikey", API_KEY)
            if (categories.isNotEmpty()) parameters.append("category", categories.joinToString(","))
            if (countries.isNotEmpty()) parameters.append("country", countries.joinToString(","))
            if (languages.isNotEmpty()) parameters.append("language", languages.joinToString(","))
        }
    }
}

private fun provideNashStoreApiService(httpClient: HttpClient): NewsDataApi =
    NewsDataApiImpl(httpClient)