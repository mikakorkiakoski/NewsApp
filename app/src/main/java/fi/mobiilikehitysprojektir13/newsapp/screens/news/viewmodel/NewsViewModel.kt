package fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.mobiilikehitysprojektir13.newsapp.data.api.NewsDataApi
import fi.mobiilikehitysprojektir13.newsapp.data.dto.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

object NewsViewModel : ViewModel() {
    private val api by inject<NewsDataApi>(NewsDataApi::class.java)

    private val _news = MutableStateFlow<News?>(null)
    val news: StateFlow<News?> = _news

    private val _article = MutableStateFlow<News.Article?>(null)
    val article: StateFlow<News.Article?> = _article

    fun searchNews(
        query: String = "",
        categories: Set<String> = emptySet(),
        countries: Set<String> = emptySet(),
        languages: Set<String> = emptySet()
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedProjects = api.getLatestNews(query, categories, countries, languages)
            _news.emit(fetchedProjects)
        }
    }

    suspend fun getArticle(articleId: String) {
        val article = _news.value?.results?.find { it.articleId == articleId }
        _article.emit(article)
    }
}