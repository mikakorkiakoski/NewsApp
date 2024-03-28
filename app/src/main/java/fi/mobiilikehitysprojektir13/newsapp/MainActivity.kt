package fi.mobiilikehitysprojektir13.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fi.mobiilikehitysprojektir13.newsapp.components.BottomBar
import fi.mobiilikehitysprojektir13.newsapp.screens.news.components.NewsToolbar
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.NewsTheme
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.components.DefaultToolbar
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.components.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val isRoot = BottomNavigationItem().bottomNavigationItems()
                    .any { it.route == currentDestination?.route }
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                        when (currentDestination?.route) {
                            Screens.News.route -> NewsToolbar()

                            else -> DefaultToolbar(navController, isRoot)
                        }
                    }, bottomBar = {
                        if (BottomNavigationItem().bottomNavigationItems().any {
                                it.route == navBackStackEntry?.destination?.route
                            }) {
                            BottomBar(navController = navController)
                        }
                    }) { paddingValues ->
                        NavigationHost(navController = navController, paddingValues = paddingValues)
                    }
                }
            }
        }
    }
}