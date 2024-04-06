package fi.mobiilikehitysprojektir13.newsapp.data.api.countries

interface RestCountriesApi {
    suspend fun getByCode(code: String): String
    suspend fun getByCountryName(country: String): String
    suspend fun getByLanguage(language: String): String
}