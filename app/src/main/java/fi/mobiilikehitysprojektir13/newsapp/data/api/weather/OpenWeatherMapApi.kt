package fi.mobiilikehitysprojektir13.newsapp.data.api.weather

import fi.mobiilikehitysprojektir13.newsapp.data.dto.OpenWeatherMap

interface OpenWeatherMapApi {
    suspend fun getCountry(
        lat: Double = 0.0,
        lon: Double = 0.0
    ): List<OpenWeatherMap>
}