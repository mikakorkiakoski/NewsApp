package fi.mobiilikehitysprojektir13.newsapp.screens.country.components

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.OpenWeatherMapViewModel
import org.osmdroid.util.GeoPoint


@Composable
fun Map() {

    val context = LocalContext.current
    var initialGeoPoint by remember {
        mutableStateOf(
            GeoPoint(
                64.9985196169378,
                25.46356201171875
            )
        )
    }
    val openWeatherMapViewModel: OpenWeatherMapViewModel = viewModel()

    LaunchedEffect(initialGeoPoint) {
        openWeatherMapViewModel.getCountry(initialGeoPoint.latitude, initialGeoPoint.longitude)
        Log.d("Geopoint: ", "$initialGeoPoint")
    }

    val cameraState = rememberCameraState {
        geoPoint = initialGeoPoint
        zoom = 12.0
    }

    val markerState = rememberMarkerState(
        geoPoint = initialGeoPoint
    )

    fun updateGeoPoint(newGeoPoint: GeoPoint) {
        markerState.geoPoint = newGeoPoint
        cameraState.geoPoint = newGeoPoint
        initialGeoPoint = newGeoPoint
        Log.d("initialGeoPoint:", "New GeoPoint: $initialGeoPoint")

    }

    val mapIcon: Drawable? by remember {
        mutableStateOf(context.getDrawable(org.osmdroid.library.R.drawable.moreinfo_arrow))
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        OpenStreetMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState,
            onMapClick = { newGeoPoint ->
                updateGeoPoint(newGeoPoint)
            }

        ) {
            Marker(
                state = markerState,
                icon = mapIcon
            )
        }
    }
}

@Preview
@Composable
fun MapPreview() {
    Map()
}
