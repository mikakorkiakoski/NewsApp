package fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.mobiilikehitysprojektir13.newsapp.data.api.OpenWeatherMapApi
import fi.mobiilikehitysprojektir13.newsapp.data.dto.OpenWeatherMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import android.util.Log

object OpenWeatherMapViewModel : ViewModel() {
    private val api by inject<OpenWeatherMapApi>(OpenWeatherMapApi::class.java)

    private val _openweathermap = MutableStateFlow<List<OpenWeatherMap>>(emptyList())
    val openweathermap: MutableStateFlow<List<OpenWeatherMap>> = _openweathermap

    fun getCountry(
        lat: Double = 0.0,
        lon: Double = 0.0
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedProjects = api.getCountry(lat, lon)
            Log.d("OpenWeatherMapViewModel", "API Response: $fetchedProjects")
            _openweathermap.emit(fetchedProjects)
        }
    }
}