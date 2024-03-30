package fi.mobiilikehitysprojektir13.newsapp.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.Dark

enum class FontSize {
    Small, Medium, Large, ExtraLarge
}

//TODO: improve visual look, add arrow icon indicating that you can open drop down menu
//TODO: functionality.
@Composable
fun SettingsScreen() {

    var selectedFontSize by remember { mutableStateOf(FontSize.Medium) }
    var isDarkTheme by remember { mutableStateOf(true) }
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "Font Size: ${selectedFontSize.name}")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "A", fontSize = 16.sp)
            Slider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    selectedFontSize = FontSize.entries[it.toInt()]
                },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                steps = 2,
                valueRange = 0f..3f
            )
            Text(text = "A", fontSize = 28.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Dark Theme", modifier = Modifier.weight(1f))
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { isDarkTheme = it },
            )
        }

        Button(
            onClick = { /* return to map screen or just give list of possible countries? */ },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text(text = "Change Country/Language", color = Dark)
        }
    }
}