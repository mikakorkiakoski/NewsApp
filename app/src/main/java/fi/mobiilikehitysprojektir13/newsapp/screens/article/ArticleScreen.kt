package fi.mobiilikehitysprojektir13.newsapp.screens.article

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
                                .border(
                                    border = BorderStroke(1.dp, Color.Gray),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = {saveArticle(articleId)}
                                ) {
                                    Icon(
                                        Icons.Outlined.FavoriteBorder, //there's no outlined star icon?
                                        contentDescription = null,
                                        Modifier.size(32.dp))
                                }
                                Text(
                                    text = "Save article",
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                    
                }
            }
        }
    }
}

fun saveArticle(id: String){

}