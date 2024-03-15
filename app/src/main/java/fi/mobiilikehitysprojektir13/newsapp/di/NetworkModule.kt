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

val networkModule = module {
    single { provideKtorClient() }
    single { provideNashStoreApiService(get()) }
}

private fun provideKtorClient(): HttpClient {
    return HttpClient(Android) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(HttpCache)
        defaultRequest {
            url {
                host = "api.fstats.dev/v2"
                protocol = URLProtocol.HTTPS
                contentType(ContentType.Application.Json)
            }
        }
    }
}

private fun provideNashStoreApiService(httpClient: HttpClient): NewsDataApi {
    return NewsDataApiImpl(httpClient)
}