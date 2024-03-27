package fi.mobiilikehitysprojektir13.newsapp.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
enum class FontSize { Small, Medium, Large, ExtraLarge }

//TODO: improve visual look, add arrow icon indicating that you can open drop down menu
//TODO: functionality.
@Composable
fun SettingsScreen(){

    var selectedFontSize by remember { mutableStateOf(FontSize.Medium) }
    var isDarkTheme by remember { mutableStateOf(true) }
    var showFontMenu by remember { mutableStateOf(false) }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Font size:", color = Color.White)

                ClickableText(
                    text = AnnotatedString(selectedFontSize.name),
                    onClick = { showFontMenu = true },
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                )
                DropdownMenu(
                    modifier = Modifier.padding(16.dp),
                    expanded = showFontMenu,
                    onDismissRequest = { showFontMenu = false }
                ) {
                    MenuItem("Small") { selectedFontSize = FontSize.Small }
                    MenuItem("Medium") { selectedFontSize = FontSize.Medium }
                    MenuItem("Large") { selectedFontSize = FontSize.Large }
                    MenuItem("Extra Large") { selectedFontSize = FontSize.ExtraLarge }
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark Theme",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { isDarkTheme = it },
                    )
            }

            Button(
                onClick = { /* open language/country selection screen */ },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Change Country/Language")
            }
        }
    }
}


@Composable
fun MenuItem(t: String, onClickAction: () -> Unit) {
    DropdownMenuItem(onClick = onClickAction,
        text = {
            Text(text = t, fontSize = 20.sp)},
    )

}