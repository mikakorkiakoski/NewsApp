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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fi.mobiilikehitysprojektir13.newsapp.data.store.UserSettingsStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class FontSize {
    Small, Medium, Large, ExtraLarge
}

@Composable
fun SettingsScreen(navController: NavController) {

    val context = LocalContext.current
    val dataStore = UserSettingsStore(context)

    val scope = rememberCoroutineScope()

    val fontSize by dataStore.getFontSize.collectAsState(FontSize.Medium)
    val isDarkMode by dataStore.isDarkMode.collectAsState(true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "Font Size: ${fontSize.name}")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "A", fontSize = 16.sp)
            Slider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                value = FontSize.entries.indexOf(fontSize).toFloat(),
                onValueChange = {
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.saveFontSize(FontSize.entries[it.toInt()])
                    }
                },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.onPrimary,
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
                checked = isDarkMode,
                onCheckedChange = {
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.enableDarkMode(it)
                    }
                },
            )
        }

        Button(
            onClick = {
                scope.launch {
                    dataStore.saveCountry("")
                    navController.navigate("map") {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = "Change Country/Language")
        }
    }
}