package fi.mobiilikehitysprojektir13.newsapp.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fi.mobiilikehitysprojektir13.newsapp.data.store.NewsStore
import fi.mobiilikehitysprojektir13.newsapp.screens.favorites.components.FavoritesItem
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel

@Composable
fun FavoritesScreen(navController: NavHostController) {

    val newsViewModel: NewsViewModel = viewModel()
    val savedArticles by newsViewModel.savedArticles.collectAsState()

    val context = LocalContext.current
    val newsStore = NewsStore(context)

    LaunchedEffect("getSavedArticles") {
        newsStore.getSavedArticles.collect {
            newsViewModel.getSavedNews(it)
        }
    }

    if (savedArticles.isEmpty()) Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No saved article")
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(savedArticles.reversed()) { article ->
            FavoritesItem(navController, article)
        }
    }
}
