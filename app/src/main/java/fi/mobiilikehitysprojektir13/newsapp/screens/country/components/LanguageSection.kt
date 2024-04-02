package fi.mobiilikehitysprojektir13.newsapp.screens.country.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//TODO: Make dropdown list scrollable to facilitate having more language options


@Composable
fun LanguageDropDown(
    languages: Set<String>,
    onLanguageSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("") }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .clickable { expanded = true }
                .fillMaxWidth()
        ) {
            BasicTextField(
                value = TextFieldValue(selectedLanguage),
                onValueChange = { selectedLanguage = it.toString() },
                enabled = false,
                textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Arrow",
                tint = Color.Gray
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    text = {
                        Text(text = language, fontSize = 20.sp)},
                    onClick = {
                        onLanguageSelected(language)
                        selectedLanguage = language
                        expanded = false
                    }
                )

            }
        }
    }
}


@Composable
fun LanguageSection(languages: Set<String>) {
    var selectedLanguage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LanguageDropDown(
            languages = languages,
            onLanguageSelected = { language ->
                selectedLanguage = language
            }
        )
    }

}


