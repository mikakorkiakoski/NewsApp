package fi.mobiilikehitysprojektir13.newsapp.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fi.mobiilikehitysprojektir13.newsapp.screens.news.components.NewsItem
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel
import fi.mobiilikehitysprojektir13.newsapp.screens.news.components.SearchBar


@Composable
fun NewsScreen(navController: NavController, modifier: Modifier = Modifier,) {
    val newsViewModel: NewsViewModel = viewModel()

    val news by newsViewModel.news.collectAsState()
    var searchText = ""

    Column {
        Surface(
            modifier = modifier,
            color = Color.DarkGray //make this prettier later
        ){
            Column {
                SearchBar(
                    onSearch = { text ->
                        searchText = text
                        println("Search text: $text")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                // CATEGORIES
                Text(text = "Categories go here")
            }

        }
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

