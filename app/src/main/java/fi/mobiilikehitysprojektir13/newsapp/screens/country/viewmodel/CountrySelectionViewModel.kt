package fi.mobiilikehitysprojektir13.newsapp.screens.country.viewmodel

import androidx.lifecycle.ViewModel
import fi.mobiilikehitysprojektir13.newsapp.data.api.weather.OpenWeatherMapApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent.inject

class CountrySelectionViewModel : ViewModel() {
    private val api: OpenWeatherMapApi by inject(OpenWeatherMapApi::class.java)

    fun getCountry(latitude: Double, longitude: Double): Flow<String> = flow {
        emit(api.getCountry(latitude, longitude).first().country)
    }
}