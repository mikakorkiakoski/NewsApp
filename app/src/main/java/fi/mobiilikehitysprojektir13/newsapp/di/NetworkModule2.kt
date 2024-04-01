package fi.mobiilikehitysprojektir13.newsapp.di

import fi.mobiilikehitysprojektir13.newsapp.data.api.OpenWeatherMapApi
import fi.mobiilikehitysprojektir13.newsapp.data.api.OpenWeatherMapApiImpl
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
private const val API_KEY = ""

val networkModule2 = module {
    single { provideKtorClient() }
    single { provideOpenWeatherMapApiService(get()) }
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
            host = "api.openweathermap.org/geo/1.0"
            protocol = URLProtocol.HTTPS
            contentType(ContentType.Application.Json)
            parameters.append("appid", API_KEY)
        }
    }
}

private fun provideOpenWeatherMapApiService(httpClient: HttpClient): OpenWeatherMapApi =
    OpenWeatherMapApiImpl(httpClient)