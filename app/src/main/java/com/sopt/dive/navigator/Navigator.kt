package com.sopt.dive.navigator

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.navigator.login.authGraph
import kotlinx.serialization.Serializable

@Serializable object Auth

@Serializable data class HomePage(val id: Int)
@Serializable data class SearchPage(val id: Int)
@Serializable data class MyPage(val id: Int)

@Composable
fun AppNavHost(navController: NavHostController, dataStore: DataStore<Preferences>, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Auth,
        modifier = modifier
    ) {
        authGraph(navController, dataStore)

        composable<HomePage> {
            Text("This is HomePage")
        }
        composable<SearchPage> {
            Text("This is SearchPage")
        }
        composable<MyPage> {
            Text("This is MyPage")
        }
    }
}