package fi.mobiilikehitysprojektir13.newsapp.screens.article

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.AsyncImage
import fi.mobiilikehitysprojektir13.newsapp.R
import fi.mobiilikehitysprojektir13.newsapp.Screens
import fi.mobiilikehitysprojektir13.newsapp.data.store.NewsStore
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

//TODO: (kind of optional) change saved/favorite icon from outline to filled if article is saved
@Composable
fun ArticleScreen(navController: NavController, navBackStackEntry: NavBackStackEntry) {

    val newsViewModel: NewsViewModel = viewModel()
    val context = LocalContext.current
    val newsStore = NewsStore(context)


    val scope = rememberCoroutineScope()

    val articleId: String? = navBackStackEntry.arguments?.getString(Screens.Article.argument)

    val article by newsViewModel.article.collectAsState()

    if (articleId == null) {
        navController.navigateUp()
        return
    }

    var isSaved by remember { mutableStateOf(false) }

    LaunchedEffect("getArticle") {
        newsViewModel.getArticle(articleId)
    }

    LaunchedEffect("getSavedArticles") {
        newsStore.getSavedArticles.collect {
            newsViewModel.getSavedNews(it)
            isSaved = it.any { it.articleId == articleId }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        item {
            Card {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = article?.imageUrl,
                    contentDescription = null
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    Row {
                        article?.category?.forEach { category ->
                            AssistChip(
                                onClick = {},
                                label = { Text(text = category.replaceFirstChar { it.uppercase() }) })
                        }
                    }
                    Text(
                        text = article?.title ?: "Title not found",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    article?.description?.let {
                        Text(text = it)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clickable {
                                    scope.launch {
                                        if (isSaved) {
                                            newsStore.removeArticle(articleId)
                                            isSaved = false
                                        } else {
                                            article?.let {
                                                newsStore.saveNewArticle(article!!)
                                                isSaved = true
                                            }
                                        }
                                    }
                                }
                                .border(
                                    border = BorderStroke(1.dp, Color.Gray),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { /* No action required here, action is handled in the Modifier.clickable */ },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isSaved) {
                                            ImageVector.vectorResource(R.drawable.baseline_star_24)
                                        } else {
                                            ImageVector.vectorResource(R.drawable.baseline_star_outline_24)
                                        },
                                        contentDescription = null,
                                        tint = Color.Yellow
                                    )
                                }
                                Text(
                                    text = if (isSaved) "Remove from favorites" else "Save to favorites",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }

                    }

                }
            }
        }
    }
}