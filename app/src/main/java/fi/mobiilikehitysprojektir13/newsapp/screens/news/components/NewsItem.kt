package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
        Column(modifier = Modifier
            .clickable {
                navController.navigate("article/${newsItem.articleId}")
            }
            .padding(8.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = (MaterialTheme.typography.bodyLarge.fontSize.value + 2).sp,
                    fontWeight = FontWeight.Bold
                ),
                text = newsItem.title,
            )
            newsItem.description?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    text = it,
                    maxLines = 1
                )
            }
        }
    }
}