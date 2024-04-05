package fi.mobiilikehitysprojektir13.newsapp.screens.country

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fi.mobiilikehitysprojektir13.newsapp.Screens
import fi.mobiilikehitysprojektir13.newsapp.data.languages
import fi.mobiilikehitysprojektir13.newsapp.data.store.UserSettingsStore
import fi.mobiilikehitysprojektir13.newsapp.screens.country.components.Location
import fi.mobiilikehitysprojektir13.newsapp.screens.country.components.LocationList
import fi.mobiilikehitysprojektir13.newsapp.screens.country.components.LocationStatus
import fi.mobiilikehitysprojektir13.newsapp.screens.country.components.Map
import fi.mobiilikehitysprojektir13.newsapp.screens.country.viewmodel.CountrySelectionViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CountrySelectionScreen(navController: NavHostController) {
    val context = LocalContext.current
    val userSettingsStore = UserSettingsStore(context)
    val country = userSettingsStore.getCountry.collectAsState(initial = "")

    val scope = rememberCoroutineScope()

    val countrySelectionViewModel: CountrySelectionViewModel = viewModel()

    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    val modeMap = remember { mutableStateOf(false) }
    val geoPoint = remember { mutableStateOf<GeoPoint>(GeoPoint(0.0, 0.0)) }

    val buttonText = when {
        modeMap.value -> "Switch to List"
        else -> "Switch to Map"
    }

    Location {
        when (it.status) {
            LocationStatus.FETCHING -> Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }

            LocationStatus.DENIED -> Scaffold(topBar = {
                CenterAlignedTopAppBar(title = { Text(text = "Select Country") })
            }, bottomBar = {
                BottomAppBar {
                    Button(modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 8.dp, end = 4.dp),
                        onClick = {
                            modeMap.value = !modeMap.value
                        }) {
                        Text(text = buttonText)
                    }
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 8.dp),
                        onClick = {
                            scope.launch {
                                when {
                                    modeMap.value -> countrySelectionViewModel.getCountry(
                                        latitude = geoPoint.value.latitude,
                                        longitude = geoPoint.value.longitude
                                    ).catch {
                                        Toast.makeText(
                                            context,
                                            "Can't get access to Reverse Geo API",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }.collect { locationCode: String? ->
                                        userSettingsStore.saveCountry(
                                            locationCode?.lowercase() ?: "en"
                                        )
                                    }

                                    else -> {
                                        if (selectedOption.isBlank()) {
                                            Toast.makeText(
                                                context,
                                                "Please select any country",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            return@launch
                                        }
                                        userSettingsStore.saveCountry(
                                            languages[selectedOption] ?: "en"
                                        )
                                    }
                                }
                            }
                            if (country.value.isNotBlank()) {
                                navController.navigate(Screens.News.route) {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                }
                            }
                        }) {
                        Text(text = "Confirm")
                    }
                }
            }) { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    when {
                        modeMap.value -> Map { point ->
                            geoPoint.value = point
                        }

                        else -> LocationList(selectedOption, onOptionSelected)
                    }
                }
            }

            LocationStatus.SUCCESS -> {
                scope.launch {
                    countrySelectionViewModel.getCountry(
                        latitude = it.location?.latitude ?: 0.0,
                        longitude = it.location?.longitude ?: 0.0
                    ).catch {
                        Toast.makeText(
                            context, "Can't get access to Reverse Geo API", Toast.LENGTH_SHORT
                        ).show()
                    }.collect { locationCode ->
                        userSettingsStore.saveCountry(locationCode.lowercase())
                    }
                }
            }
        }
        if (country.value.isNotBlank()) {
            navController.navigate(Screens.News.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }
}
