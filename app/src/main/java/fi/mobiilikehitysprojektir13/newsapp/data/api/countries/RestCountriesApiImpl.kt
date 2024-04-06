package fi.mobiilikehitysprojektir13.newsapp.data.api.countries

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

class RestCountriesApiImpl(private val httpClient: HttpClient) : RestCountriesApi {
    override suspend fun getByCode(code: String): String = httpClient.get("alpha/${code}") {
        host = "restcountries.com/v3.1"
        parameter("fields", "languages,cca2")
    }.bodyAsText()

    override suspend fun getByCountryName(country: String): String =
        httpClient.get("name/${country}") {
            host = "restcountries.com/v3.1"
            parameter("fields", "languages,cca2")
        }.bodyAsText()

    override suspend fun getByLanguage(language: String): String =
        httpClient.get("lang/${language}") {
            host = "restcountries.com/v3.1"
            parameter("fields", "languages,cca2")
        }.bodyAsText()
}