package fi.mobiilikehitysprojektir13.newsapp.data.api

import android.util.Log
import fi.mobiilikehitysprojektir13.newsapp.data.dto.OpenWeatherMap
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.request

class OpenWeatherMapApiImpl(private val httpClient: HttpClient) : OpenWeatherMapApi {
    override suspend fun getCountry(lat: Double, lon: Double): List<OpenWeatherMap> {

        val limit = 1
        val request = httpClient.get("reverse") {
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("limit", limit)
        }

        Log.d("getCountry: ", request.request.url.toString())

        return request.body<List<OpenWeatherMap>>()
    }
}