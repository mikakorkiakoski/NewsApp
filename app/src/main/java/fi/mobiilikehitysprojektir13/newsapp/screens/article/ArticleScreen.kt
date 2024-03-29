package fi.mobiilikehitysprojektir13.newsapp.screens.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.AsyncImage
import fi.mobiilikehitysprojektir13.newsapp.Screens
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel

@Composable
fun ArticleScreen(navController: NavController, navBackStackEntry: NavBackStackEntry) {

    val newsViewModel: NewsViewModel = viewModel()

    val articleId: String? = navBackStackEntry.arguments?.getString(Screens.Article.argument)

    val article by newsViewModel.article.collectAsState()

    if (articleId == null) {
        navController.navigateUp()
        return
    }

    LaunchedEffect("getArticle") {
        newsViewModel.getArticle(articleId)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Card {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = article?.imageUrl,
                contentDescription = null
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    article?.category?.forEach {category ->
                        AssistChip(onClick = {}, label =  { Text(text = category.replaceFirstChar { it.uppercase() })})
                    }
                }
                Text(
                    text = article?.title ?: "Title not found",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(text = article?.description ?: "No description :(")
            }
        }
    }
}