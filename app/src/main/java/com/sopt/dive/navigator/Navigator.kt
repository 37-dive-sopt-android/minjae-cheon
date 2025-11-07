package com.sopt.dive.navigator

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.navigator.login.authGraph
import kotlinx.serialization.Serializable

// ============ Navigation Graph Markers ============
@Serializable
data object AuthGraph

@Serializable
data object MainGraph

// ============ Route Hierarchy ============
// Interface: navigation에 직접 사용 X
sealed interface Route

sealed interface Auth : Route
sealed interface Main : Route

// ============ Concrete Routes ============
@Serializable
data object Login : Auth

@Serializable
data object SignUp : Auth

@Serializable
data object HomePage : Main

@Serializable
data object SearchPage : Main

@Serializable
data object MyPage : Main

@Composable
fun AppNavHost(navController: NavHostController, dataStore: DataStore<Preferences>,
               snackbarHostState: SnackbarHostState,
               modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = AuthGraph,
        modifier = modifier
    ) {
        authGraph(
            navController =  navController,
            dataStore =  dataStore,
            snackbarHostState = snackbarHostState
        )

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