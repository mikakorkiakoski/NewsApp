package fi.mobiilikehitysprojektir13.newsapp.screens.country.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.util.GeoPoint


@Composable
fun Map() {
    val context = LocalContext.current

    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(-6.3970066, 106.8224316)
        zoom = 12.0
    }

    val depokMarkerState = rememberMarkerState(
        geoPoint = GeoPoint(-6.3970066, 106.8224316)
    )

    val depokIcon: Drawable? by remember {
        mutableStateOf(context.getDrawable(org.osmdroid.library.R.drawable.moreinfo_arrow))
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        OpenStreetMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState
        ) {
            Marker(
                state = depokMarkerState,
                icon = depokIcon
            )
        }
    }
}

@Preview
@Composable
fun MapPreview() {
    Map()
}
