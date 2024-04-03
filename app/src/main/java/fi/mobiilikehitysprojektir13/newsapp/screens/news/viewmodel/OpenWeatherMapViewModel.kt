package fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.mobiilikehitysprojektir13.newsapp.data.api.OpenWeatherMapApi
import fi.mobiilikehitysprojektir13.newsapp.data.dto.OpenWeatherMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

object OpenWeatherMapViewModel : ViewModel() {
    private val api: OpenWeatherMapApi by inject(OpenWeatherMapApi::class.java)

    private val _openWeatherMap = MutableStateFlow<List<OpenWeatherMap>>(emptyList())
    val openWeatherMap: MutableStateFlow<List<OpenWeatherMap>> = _openWeatherMap

    fun getCountry(
        lat: Double = 0.0, lon: Double = 0.0
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedProjects = api.getCountry(lat, lon)
            Log.d("OpenWeatherMapViewModel", "API Response: $fetchedProjects")
            _openWeatherMap.emit(fetchedProjects)
        }
    }
}