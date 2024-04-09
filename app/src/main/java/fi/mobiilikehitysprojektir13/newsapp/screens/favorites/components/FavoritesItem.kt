package fi.mobiilikehitysprojektir13.newsapp.screens.favorites.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fi.mobiilikehitysprojektir13.newsapp.data.dto.News

@Composable
fun FavoritesItem(navController: NavController, favoritesItem: News.Article) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Column(modifier = Modifier
            .clickable {
                navController.navigate("article/${favoritesItem.articleId}")
            }
            .padding(8.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                text = favoritesItem.title,
            )
            favoritesItem.description?.let {
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