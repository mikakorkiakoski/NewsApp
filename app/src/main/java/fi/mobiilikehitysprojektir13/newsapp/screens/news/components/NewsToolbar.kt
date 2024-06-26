package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import fi.mobiilikehitysprojektir13.newsapp.data.store.UserSettingsStore
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel

@Composable
fun NewsToolbar() {
    Column {

        val context = LocalContext.current
        val userSettingsStore = UserSettingsStore(context)
        val country by userSettingsStore.getCountry.collectAsState("")
        val language by userSettingsStore.getLanguage.collectAsState("")

        val newsViewModel: NewsViewModel = viewModel()

        val query = remember { mutableStateOf("") }
        val categories = remember { mutableStateOf(setOf<String>()) }

        LaunchedEffect(query.value, categories.value) {
            runCatching {
                newsViewModel.searchNews(
                    query = query.value,
                    categories = categories.value,
                    countries = setOf(country),
                    languages = setOf(language)
                )
            }.onFailure {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("NewsToolbar: ", it.stackTraceToString())
                newsViewModel.stopLoading()
            }
        }

        SearchBar(onSearch = {
            query.value = it
            newsViewModel.clearNewsHistory()
        })

        CategoryChips(onChange = {
            categories.value = setOfNotNull(it)
            newsViewModel.clearNewsHistory()
        })
    }
}