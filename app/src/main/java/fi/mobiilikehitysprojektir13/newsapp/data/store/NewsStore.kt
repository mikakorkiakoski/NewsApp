package fi.mobiilikehitysprojektir13.newsapp.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import fi.mobiilikehitysprojektir13.newsapp.data.dto.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NewsStore(private val context: Context) {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    companion object {
        private val Context.dataStorage: DataStore<Preferences> by preferencesDataStore("saved_news")
        val NEWS_LIST_TOKEN_KEY = stringPreferencesKey("news")
    }

    val getSavedArticles: Flow<List<News.Article>> = context.dataStorage.data.map { preferences ->
        (preferences[NEWS_LIST_TOKEN_KEY]?.let {
            json.decodeFromString<List<News.Article>>(it)
        } ?: emptySet()).toList()
    }

    suspend fun saveArticles(articles: List<News.Article>) {
        context.dataStorage.edit { preferences ->
            preferences[NEWS_LIST_TOKEN_KEY] = json.encodeToString(articles)
        }
    }
}