package fi.mobiilikehitysprojektir13.newsapp.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fi.mobiilikehitysprojektir13.newsapp.data.dto.News
import fi.mobiilikehitysprojektir13.newsapp.screens.news.components.EndlessLazyColumn
import fi.mobiilikehitysprojektir13.newsapp.screens.news.components.NewsItem
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel


@Composable
fun NewsScreen(navController: NavController) {
    val newsViewModel: NewsViewModel = viewModel()

    val news by newsViewModel.news.collectAsState()

    val loadingState by newsViewModel.loading.collectAsState()


    if (news.isEmpty()) Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "News not found")
    }

    EndlessLazyColumn(
        loading = loadingState,
        items = news.toList(),
        itemKey = { article: News.Article -> article.articleId },
        itemContent = { article: News.Article ->
            NewsItem(navController, article)
            Spacer(modifier = Modifier.height(8.dp))
        },
        loadingItem = { CircularProgressIndicator() },
    ) {
        newsViewModel.loadMore()
    }
}

