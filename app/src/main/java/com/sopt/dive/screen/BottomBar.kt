package com.sopt.dive.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.sopt.dive.navigator.HomePage
import com.sopt.dive.navigator.Main
import com.sopt.dive.navigator.MyPage
import com.sopt.dive.navigator.Route
import com.sopt.dive.navigator.SearchPage

enum class BottomBarItem(val title: String, val icon: ImageVector, val route: Main) {
    HOME("Home Page", Icons.Default.Home, HomePage),
    MY("My Page", Icons.Default.Person, MyPage),
    SEARCH("Search", Icons.Default.Search, SearchPage)
}

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        BottomBarItem.entries.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hasRoute(item.route::class) ?: false,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(item.icon, contentDescription = "bottom icon") },
                label = { Text(item.title) },
            )
        }
    }
}