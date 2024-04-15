package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp)),
                model = newsItem.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            if (newsItem.imageUrl.isNullOrEmpty()) {
                Text(
                    text = "Image Unavailable",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 90.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
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