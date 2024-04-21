package fi.mobiilikehitysprojektir13.newsapp.screens.country.components

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.utsman.osmandcompose.CameraProperty
import com.utsman.osmandcompose.CameraState
import com.utsman.osmandcompose.MapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.MarkerState
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import fi.mobiilikehitysprojektir13.newsapp.R
import org.osmdroid.util.GeoPoint


@Composable
fun Map(selectedLocation: (GeoPoint), geoPointBlock: (GeoPoint) -> Unit) {

    val context = LocalContext.current

    var initialGeoPoint by remember {
        mutableStateOf(
            GeoPoint(selectedLocation)
        )
    }

    LaunchedEffect(initialGeoPoint) {
        geoPointBlock(initialGeoPoint)
    }

    OpenStreetMap(
        modifier = Modifier.fillMaxSize(), cameraState = CameraState(
            CameraProperty(
                geoPoint = initialGeoPoint, zoom = 1.0
            )
        ), onMapClick = { newGeoPoint ->
            initialGeoPoint = newGeoPoint
        }, properties = MapProperties(zoomButtonVisibility = ZoomButtonVisibility.NEVER)
    ) {
        if (initialGeoPoint.latitude != 0.0 || initialGeoPoint.longitude != 0.0) {
            Marker(
                state = MarkerState(initialGeoPoint), icon = AppCompatResources.getDrawable(
                    context, R.drawable.baseline_location_on_48
                )?.apply { setTint(Color.Red.toArgb()) }
            )
        }
    }
}