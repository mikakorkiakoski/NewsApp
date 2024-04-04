package fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel

import androidx.lifecycle.ViewModel
import fi.mobiilikehitysprojektir13.newsapp.data.api.weather.OpenWeatherMapApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent.inject

object OpenWeatherMapViewModel : ViewModel() {
    private val api: OpenWeatherMapApi by inject(OpenWeatherMapApi::class.java)

    fun getCountry(lat: Double, lon: Double): Flow<String> = flow {
        api.getCountry(lat, lon)[0].country
    }
}