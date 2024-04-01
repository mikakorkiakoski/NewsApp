package fi.mobiilikehitysprojektir13.newsapp.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import fi.mobiilikehitysprojektir13.newsapp.BottomNavigationItem
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.Blue
import fi.mobiilikehitysprojektir13.newsapp.ui.theme.Dark

@Composable
fun BottomBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (!BottomNavigationItem().bottomNavigationItems().any {
            it.route == currentDestination?.route
        }) return

    NavigationBar {
        BottomNavigationItem().bottomNavigationItems().forEach { navigationItem ->
            NavigationBarItem(selected = navigationItem.route == currentDestination?.route,
                label = {
                    Text(navigationItem.label)
                },
                icon = {
                    Icon(navigationItem.icon, navigationItem.label)
                },
                onClick = {
                    navController.popBackStack()
                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Blue,
                    selectedTextColor = Blue,
                    indicatorColor = Dark,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                )
            )
        }
    }
}