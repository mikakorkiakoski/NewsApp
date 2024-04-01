package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.Light

@Composable
fun NewsToolbar() {
    Column(modifier = Modifier.background(color = Light)) {

        val newsViewModel: NewsViewModel = viewModel()

        val query = remember { mutableStateOf("") }
        val categories = remember { mutableStateOf(setOf<String>()) }

        LaunchedEffect(query.value, categories.value) {
            newsViewModel.searchNews(query = query.value, categories = categories.value)
            Log.e("NewsToolbar: ", "${query.value} | ${categories.value}")
        }

        SearchBar(onSearch = {
            query.value = it
        })

        CategoryChips(onChange = {
            categories.value = setOf(it)
        })
    }
}