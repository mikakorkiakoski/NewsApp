package fi.mobiilikehitysprojektir13.newsapp.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class OpenWeatherMap(
    val country: String
)