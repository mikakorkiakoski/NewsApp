package fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel

import androidx.lifecycle.ViewModel
import fi.mobiilikehitysprojektir13.newsapp.data.api.news.NewsDataApi
import fi.mobiilikehitysprojektir13.newsapp.data.dto.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.java.KoinJavaComponent.inject

object NewsViewModel : ViewModel() {

    data class PreviousSearch(
        val query: String = "",
        val categories: Set<String> = emptySet(),
        val countries: Set<String> = emptySet(),
        val languages: Set<String> = emptySet()
    )

    private val api: NewsDataApi by inject(NewsDataApi::class.java)

    private val _nextPage = MutableStateFlow("")
    private val previousSearch = MutableStateFlow<PreviousSearch?>(null)

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _news = MutableStateFlow<Set<News.Article>>(emptySet())
    val news: StateFlow<Set<News.Article>> = _news

    private val _article = MutableStateFlow<News.Article?>(null)
    val article: StateFlow<News.Article?> = _article

    private val _savedArticles = MutableStateFlow<List<News.Article>>(emptyList())
    val savedArticles: StateFlow<List<News.Article>> = _savedArticles.asStateFlow()

    suspend fun searchNews(
        query: String = "",
        categories: Set<String>? = null,
        countries: Set<String> = emptySet(),
        languages: Set<String> = emptySet(),
        news: String = ""
    ) {
        _loading.value = true

        val fetchedProjects = api.getLatestNews(query, categories ?: emptySet(), countries, languages, news)

        _nextPage.update {
            fetchedProjects.nextPage ?: ""
        }

        _news.update {
            val updatedSet = it.toMutableSet().apply { addAll(fetchedProjects.results) }
            updatedSet
        }

        _loading.value = false

        previousSearch.value = PreviousSearch(query, categories ?: emptySet(), countries, languages)
    }

    fun clearNewsHistory() {
        _news.update {
            emptySet()
        }
    }

    fun stopLoading() {
        _loading.update {
            false
        }
    }

    suspend fun loadMore() {
        previousSearch.value?.apply {
            searchNews(query, categories, countries, languages, _nextPage.value)
        }
    }

    suspend fun getArticle(articleId: String) {
        val article = _news.value.find { it.articleId == articleId }
        _article.emit(article)
    }

    suspend fun getSavedNews(savedArticles: List<News.Article>) {
        _savedArticles.emit(savedArticles)
    }

    fun addArticle(article: News.Article) {
        _savedArticles.update { current ->
            val updatedList = current.toMutableList().apply { add(article) }
            updatedList
        }
    }

    fun removeSavedArticle(articleId: String) {
        _savedArticles.update { savedArticles ->
            savedArticles.filterNot { it.articleId == articleId }
        }
    }
}
