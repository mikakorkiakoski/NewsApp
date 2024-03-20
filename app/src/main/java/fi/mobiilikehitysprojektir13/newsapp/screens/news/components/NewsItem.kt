package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fi.mobiilikehitysprojektir13.newsapp.data.dto.News

@Composable
fun NewsItem(navController: NavController, newsItem: News.Article) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    navController.navigate("article/${newsItem.articleId}")
                }
                .padding(8.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                text = newsItem.title,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                text = newsItem.description ?: "No description :(",
                maxLines = 1
            )
        }
    }
}