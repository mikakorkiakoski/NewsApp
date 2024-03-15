package fi.mobiilikehitysprojektir13.newsapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val status: String,
    val totalResults: Int,
    val results: List<Article> = emptyList()
) {
    @Serializable
    data class Article(
        @SerialName("article_id")
        val articleId: String,
        val title: String,
        val link: String,
        val creator: List<String>? = emptyList(),
        val description: String?,
        val content: String,
        val pubDate: String,
        @SerialName("image_url")
        val imageUrl: String?,
        @SerialName("source_id")
        val sourceId: String,
        @SerialName("source_url")
        val sourceUrl: String,
        @SerialName("source_icon")
        val sourceIcon: String?,
        val country: List<String> = emptyList(),
        val category: List<String> = emptyList(),
        val language: String
    )
}