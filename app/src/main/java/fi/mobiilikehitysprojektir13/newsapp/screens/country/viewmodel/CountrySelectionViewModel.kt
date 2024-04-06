package fi.mobiilikehitysprojektir13.newsapp.screens.country.viewmodel

import androidx.lifecycle.ViewModel
import fi.mobiilikehitysprojektir13.newsapp.data.api.countries.RestCountriesApi
import fi.mobiilikehitysprojektir13.newsapp.data.api.weather.OpenWeatherMapApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent.inject

class CountrySelectionViewModel : ViewModel() {
    private val weatherMapApi: OpenWeatherMapApi by inject(OpenWeatherMapApi::class.java)
    private val restCountriesApi: RestCountriesApi by inject(RestCountriesApi::class.java)

    fun getCountry(latitude: Double, longitude: Double): Flow<String> = flow {
        emit(weatherMapApi.getCountry(latitude, longitude).first().country)
    }

    fun getLanguageByCountryCode(code: String): Flow<String> = flow {
        emit(restCountriesApi.getByCode(code))
    }

    fun getLanguageByName(country: String): Flow<String> = flow {
        emit(restCountriesApi.getByCountryName(country))
    }
}