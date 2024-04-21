package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChips(onChange: (category: String?) -> Unit) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    val categories = setOf(
        "business",
        "crime",
        "domestic",
        "education",
        "entertainment",
        "environment",
        "food",
        "health",
        "lifestyle",
        "other",
        "politics",
        "science",
        "sports",
        "technology",
        "tourism",
        "world"
    )

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp)
    ) {
        categories.forEachIndexed { index, category ->
            val isSelected = index == selectedIndex
            FilterChip(onClick = {
                if (selectedIndex != index) {
                    selectedIndex = index
                    onChange(categories.elementAt(index))
                } else {
                    selectedIndex = null
                    onChange(null)  // Return null to indicate no category is selected
                }
            }, label = {
                Text(text = category.replaceFirstChar { it.titlecase() })
            }, selected = isSelected, leadingIcon = {
                if (isSelected) Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }, modifier = Modifier.padding(horizontal = 2.dp))
        }
    }
}