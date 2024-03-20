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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChips(onChange: (categories: Set<String>) -> Unit) {

    val selectedCategories = remember { mutableStateListOf<String>() }

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
            "top",
            "tourism",
            "world"
        ).forEach { category ->
            val isSelected = selectedCategories.contains(category)
            FilterChip(onClick = {
                when {
                    isSelected -> selectedCategories.remove(category)
                    else -> selectedCategories.add(category)
                }
                onChange.invoke(selectedCategories.toSet())
            }, label = {
                Text(text = category.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                    color = if (isSelected) Color.Black else Color.White)
            }, selected = isSelected, leadingIcon = {
                if (isSelected) Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    tint = Color.Black,
                    modifier = Modifier.size(FilterChipDefaults.IconSize)

                )
            }, modifier = Modifier.padding(horizontal = 2.dp))
        }
    }
}

@Preview
@Composable
fun PreviewCategoryChips() {
    CategoryChips {

    }
}

