package fi.mobiilikehitysprojektir13.newsapp.data.api.weather

import fi.mobiilikehitysprojektir13.newsapp.data.dto.OpenWeatherMap
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.request.parameter

class OpenWeatherMapApiImpl(private val httpClient: HttpClient) : OpenWeatherMapApi {

    companion object {
        // WARNING Put key here but DON'T commit it
        private const val GEO_API_KEY = "ff16f3d67d0e7ddd64bc416bef1ee4da"
    }

    override suspend fun getCountry(lat: Double, lon: Double): List<OpenWeatherMap> =
        httpClient.get("reverse") {
            host = "api.openweathermap.org/geo/1.0"
            parameter("appid", GEO_API_KEY)
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("limit", 1)
        }.body<List<OpenWeatherMap>>()
}