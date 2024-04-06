package fi.mobiilikehitysprojektir13.newsapp.di

import fi.mobiilikehitysprojektir13.newsapp.data.api.countries.RestCountriesApi
import fi.mobiilikehitysprojektir13.newsapp.data.api.countries.RestCountriesApiImpl
import fi.mobiilikehitysprojektir13.newsapp.data.api.news.NewsDataApi
import fi.mobiilikehitysprojektir13.newsapp.data.api.news.NewsDataApiImpl
import fi.mobiilikehitysprojektir13.newsapp.data.api.weather.OpenWeatherMapApi
import fi.mobiilikehitysprojektir13.newsapp.data.api.weather.OpenWeatherMapApiImpl
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
    single { provideNewsKtorClient() }
    single { provideNewsDataApiService(get()) }
    single { provideOpenWeatherMapApiService(get()) }
    single { provideRestCountriesService(get()) }
}

private fun provideNewsKtorClient(): HttpClient = HttpClient(Android) {
    expectSuccess = true
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
    install(HttpCache)
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            contentType(ContentType.Application.Json)
        }
    }
}

private fun provideNewsDataApiService(httpClient: HttpClient): NewsDataApi =
    NewsDataApiImpl(httpClient)

private fun provideOpenWeatherMapApiService(httpClient: HttpClient): OpenWeatherMapApi =
    OpenWeatherMapApiImpl(httpClient)

private fun provideRestCountriesService(httpClient: HttpClient): RestCountriesApi =
    RestCountriesApiImpl(httpClient)