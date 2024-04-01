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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChips(onChange: (category: String) -> Unit) {
    // Use an index to keep track of the selected category
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp)
    ) {
        setOf(

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
        ).forEachIndexed { index, category ->
            val isSelected = index == selectedIndex
            FilterChip(onClick = {
                // Update the selectedIndex to the current index
                selectedIndex = index
                selectedIndex?.let { onChange.invoke(setOf("Politics", "Business", "Technology", "Science", "Health", "Entertainment", "Sports").elementAt(it)) }
            }, label = {
                Text(text = category.replaceFirstChar {  it.titlecase()  },
                    color = if (isSelected) Color.Black else Color.White)
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