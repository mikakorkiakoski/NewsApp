package fi.mobiilikehitysprojektir13.newsapp.screens.news

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fi.mobiilikehitysprojektir13.newsapp.data.dto.News
import fi.mobiilikehitysprojektir13.newsapp.screens.news.components.EndlessLazyColumn
import fi.mobiilikehitysprojektir13.newsapp.screens.news.components.NewsItem
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsScreen(navController: NavController) {
    val newsViewModel: NewsViewModel = viewModel()

    val news by newsViewModel.news.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val loadingState by newsViewModel.loading.collectAsState()

    val isRefreshing by newsViewModel.isRefreshing.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(isRefreshing, { newsViewModel.refreshNews() })

    Box(
        Modifier
            .pullRefresh(pullRefreshState)
    ) {

        if (news.isEmpty() && !loadingState) Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "News not found")
        }

        if (news.isEmpty() && loadingState) Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
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
            scope.launch {
                runCatching {
                    newsViewModel.loadMore()
                }.onFailure {
                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    Log.e("NewsScreen: ", it.stackTraceToString())
                    newsViewModel.stopLoading()
                }
            }
        }
        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

