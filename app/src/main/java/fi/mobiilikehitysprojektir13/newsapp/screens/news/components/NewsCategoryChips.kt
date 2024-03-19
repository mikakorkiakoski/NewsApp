package fi.mobiilikehitysprojektir13.newsapp.screens.news.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

@Composable
fun CategoryChips(categories: List<String>) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        categories.forEach { category ->
            val isSelected = category == selectedCategory
            FilterChip(
                onClick = {
                    selectedCategory = if (isSelected) null else category
                    Log.d("CategoryChips", "Selected category: $selectedCategory")
                },
                label = {
                    Text(
                        text = category,
                        color = if (isSelected) Color.Black else Color.White
                    )
                },
                selected = isSelected,
                leadingIcon = if (isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            tint = if (isSelected) Color.Black else Color.White,
                            modifier = Modifier.size(FilterChipDefaults.IconSize)

                        )
                    }
                } else {
                    null
                },
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCategoryChips() {
    val categories =
        listOf("Politics", "Business", "Technology", "Science", "Health", "Entertainment", "Sports")
    CategoryChips(categories)
}

