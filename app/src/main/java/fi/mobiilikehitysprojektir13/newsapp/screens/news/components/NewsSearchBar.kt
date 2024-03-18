package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchTextState = remember { mutableStateOf("") }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
            TextField(
                value = searchTextState.value,
                onValueChange = {
                    searchTextState.value = it
                },
                singleLine = true,
                placeholder = { Text(text = placeholder) },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    println("done")
                    onSearch(searchTextState.value)
                    keyboardController?.hide()
                })
            )
            IconButton(onClick = { searchTextState.value = "" }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Icon"
                )
            }
        }
}

@Preview
@Composable
fun PreviewSearchBar() {
    var searchText = ""
    SearchBar(onSearch = { text ->
        searchText = text
        println("Search text: $searchText")
    },)
}


