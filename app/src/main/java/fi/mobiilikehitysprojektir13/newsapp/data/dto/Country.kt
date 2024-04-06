package fi.mobiilikehitysprojektir13.newsapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val cca2: String,
    val languages: Map<String, String>
)
