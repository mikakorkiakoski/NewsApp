package fi.mobiilikehitysprojektir13.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.Dark
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.NewsTheme
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.components.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(Dark),
                            Alignment.Center
                        ) {
                            Text(text = "Header")
                        }
                    }, bottomBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(Dark),
                            Alignment.Center
                        ) {
                            Text(text = "Bottom")
                        }
                    }) { paddingValues ->
                        NavigationHost(
                            navController = navController,
                            paddingValues = paddingValues
                        )
                    }
                }
            }
        }
    }
}