package fi.mobiilikehitysprojektir13.newsapp.data.api

import fi.mobiilikehitysprojektir13.newsapp.data.dto.News

interface NewsDataApi {
    suspend fun getLatestNews(): News
}