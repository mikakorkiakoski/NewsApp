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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import fi.mobiilikehitysprojektir13.newsapp.Screens
import fi.mobiilikehitysprojektir13.newsapp.data.countries
import fi.mobiilikehitysprojektir13.newsapp.data.dto.Country
import fi.mobiilikehitysprojektir13.newsapp.data.languages
import fi.mobiilikehitysprojektir13.newsapp.data.store.UserSettingsStore
import fi.mobiilikehitysprojektir13.newsapp.screens.country.components.CountryList
import fi.mobiilikehitysprojektir13.newsapp.screens.country.components.Location
import fi.mobiilikehitysprojektir13.newsapp.screens.country.components.LocationStatus
import fi.mobiilikehitysprojektir13.newsapp.screens.country.components.Map
import fi.mobiilikehitysprojektir13.newsapp.screens.country.viewmodel.CountrySelectionViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.osmdroid.util.GeoPoint

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CountrySelectionScreen(navController: NavHostController) {
    val context = LocalContext.current
    val userSettingsStore = UserSettingsStore(context)
    val country = userSettingsStore.getCountry.collectAsState(initial = "")

    Location {
        when (it.status) {
            LocationStatus.FETCHING -> Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }

            LocationStatus.DENIED -> Selector(navController, GeoPoint(0.0, 0.0))

            LocationStatus.SUCCESS -> Selector(
                navController, GeoPoint(it.location?.latitude ?: 0.0, it.location?.longitude ?: 0.0)
            )
        }
        if (country.value.isNotBlank()) navController.navigate(Screens.News.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Selector(navController: NavController, initGeoPoint: GeoPoint) {
    val context = LocalContext.current
    val userSettingsStore = UserSettingsStore(context)
    val country = userSettingsStore.getCountry.collectAsState(initial = "")

    val scope = rememberCoroutineScope()

    val countrySelectionViewModel: CountrySelectionViewModel = viewModel()

    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    val modeMap = remember { mutableStateOf(false) }
    val geoPoint = remember { mutableStateOf(initGeoPoint) }

    LaunchedEffect("get_country") {
        if (geoPoint.value.latitude != 0.0 && geoPoint.value.longitude != 0.0) countrySelectionViewModel.getCountry(
            geoPoint.value.latitude, geoPoint.value.longitude
        ).catch {
            Toast.makeText(
                context,
                "Can't get access to Reverse Geo API or Location is incorrect",
                Toast.LENGTH_SHORT
            ).show()
        }.collect { country ->
            countries.entries.find { it.value == country.lowercase() }?.let {
                onOptionSelected(it.key)
            }
        }
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Select Country") })
    }, bottomBar = {
        BottomAppBar {
            Button(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 8.dp, end = 4.dp),
                onClick = {
                    modeMap.value = !modeMap.value
                }) {
                Text(
                    text = when {
                        modeMap.value -> "Switch to List"
                        else -> "Switch to Map"
                    }
                )
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 8.dp), onClick = {
                scope.launch {
                    when {
                        modeMap.value -> countrySelectionViewModel.getCountry(
                            latitude = geoPoint.value.latitude,
                            longitude = geoPoint.value.longitude
                        ).catch {
                            Toast.makeText(
                                context,
                                "Can't get access to Reverse Geo API or Location is incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        }.collect { locationCode: String ->
                            countrySelectionViewModel.getLanguageByCountryCode(
                                locationCode
                            ).catch {
                                Toast.makeText(
                                    context,
                                    "Can't get access to RestCountries API or Location is incorrect",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }.collect { country ->
                                parseCountry(country, userSettingsStore)
                            }
                            userSettingsStore.saveCountry(
                                countries.entries.firstOrNull { entry ->
                                    entry.value == locationCode.lowercase()
                                }?.value ?: "us"
                            )
                        }

                        else -> {
                            if (selectedOption.isBlank()) {
                                Toast.makeText(
                                    context, "Please select any country", Toast.LENGTH_SHORT
                                ).show()
                                return@launch
                            }

                            countrySelectionViewModel.getLanguageByName(
                                selectedOption
                            ).catch {
                                Toast.makeText(
                                    context,
                                    "Can't get access to RestCountries API or Location is incorrect",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }.collect { country ->
                                parseCountry(country, userSettingsStore)
                            }
                            userSettingsStore.saveCountry(
                                countries[selectedOption] ?: "us"
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
                modeMap.value -> Map(geoPoint.value) { point ->
                    geoPoint.value = point
                }

                else -> CountryList(selectedOption, onOptionSelected)
            }
        }
    }
}

suspend fun parseCountry(country: String, userSettingsStore: UserSettingsStore) {
    try {
        val list = Json.decodeFromString<List<Country>>(country)
        userSettingsStore.saveLanguage(
            languages[list.firstOrNull()?.languages?.entries?.find { entry ->
                languages.containsKey(entry.value)
            }?.value] ?: "en"
        )
    } catch (e: SerializationException) {
        try {
            val single = Json.decodeFromString<Country>(country)
            userSettingsStore.saveLanguage(
                languages[single.languages.entries.find { entry ->
                    languages.containsKey(entry.value)
                }?.value] ?: "en"
            )
        } catch (e: SerializationException) {
            throw IllegalStateException("Unknown response structure")
        }
    }
}