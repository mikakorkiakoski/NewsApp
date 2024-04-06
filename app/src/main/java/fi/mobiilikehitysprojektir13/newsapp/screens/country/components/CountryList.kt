package fi.mobiilikehitysprojektir13.newsapp.screens.country.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fi.mobiilikehitysprojektir13.newsapp.data.countries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryList(selectedOption: String, onOptionSelected: (String) -> Unit) {

    val (searchQuery, onQueryChange) = remember { mutableStateOf("") }

    SearchBar(query = searchQuery,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = true,
        onActiveChange = {},
        placeholder = {
            Text(text = "Enter country name")
        }) {
        LazyColumn {
            countries.keys.filter { country -> country.contains(searchQuery, true) }
                .forEach { country ->
                    item {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(selected = country == selectedOption,
                                    onClick = { onOptionSelected(country) }),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = country == selectedOption,
                                onClick = {
                                    onOptionSelected(country)
                                })
                            Text(text = country)
                        }
                    }
                }
        }
    }
}