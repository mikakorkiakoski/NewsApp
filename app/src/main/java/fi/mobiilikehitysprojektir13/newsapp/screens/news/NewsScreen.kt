package fi.mobiilikehitysprojektir13.newsapp.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fi.mobiilikehitysprojektir13.newsapp.screens.news.components.NewsItem
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel

@Composable
fun NewsScreen(navController: NavController) {
    val newsViewModel: NewsViewModel = viewModel()

    val news by newsViewModel.news.collectAsState()

    Column {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            if (news != null) {
                items(news!!.results) {
                    NewsItem(navController, it)
                }
            }
        }
    }
}