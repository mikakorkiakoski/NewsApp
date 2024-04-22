package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    placeholder: String = "Search",
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchTextState = remember { mutableStateOf("") }

    TextField(value = searchTextState.value,
        onValueChange = {
            searchTextState.value = it
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background
        ),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(searchTextState.value)
            keyboardController?.hide()
        }),
        trailingIcon = {
            if (searchTextState.value != "") IconButton(onClick = {
                searchTextState.value = ""
            }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear Icon")
            }
        })
}

@Preview
@Composable
fun PreviewSearchBar() {
    var searchText: String
    SearchBar(
        onSearch = { text ->
            searchText = text
            println("Search text: $searchText")
        },
    )
}


