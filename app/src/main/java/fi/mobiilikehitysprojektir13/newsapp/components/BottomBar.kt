package fi.mobiilikehitysprojektir13.newsapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomBar (navController: NavController) {
    Row {
        NavigationButton(
            icon = Icons.Default.Home,
            contentDescription = "Home icon",
            onClick = {
            /* navigate */ }
        )
        Spacer(modifier = Modifier.width(16.dp))
        NavigationButton(
            icon = Icons.Default.Star,
            contentDescription = "Starred icon",
            onClick = { /* navigate */ }
        )
        Spacer(modifier = Modifier.width(16.dp))
        NavigationButton(
            icon = Icons.Default.Settings,
            contentDescription = "Settings icon",
            onClick = {  }
        )
    }
}

@Composable
fun NavigationButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = LessRoundedShape
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(48.dp)
        )
    }
}

private val LessRoundedShape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)