package com.sopt.dive.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.lifecycleScope
import com.sopt.dive.data.UserRepository
import com.sopt.dive.navigator.AppNavHost
import com.sopt.dive.navigator.HomePage
import com.sopt.dive.navigator.MyPage
import com.sopt.dive.navigator.SearchPage
import com.sopt.dive.screen.BottomBar
import com.sopt.dive.ui.theme.DiveTheme
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_info")
class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataStore: DataStore<Preferences> by lazy {
            this.dataStore
        }

            setContent {
            DiveTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val shouldShowBottomBar = currentDestination?.let { dest ->
                    dest.hasRoute<HomePage>() || dest.hasRoute<MyPage>() || dest.hasRoute<SearchPage>()
                } ?: false

                Scaffold(
                    bottomBar = {
                        if(shouldShowBottomBar) {
                            BottomBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        dataStore = dataStore,
                        snackbarHostState = snackbarHostState,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            lifecycleScope.launch {
                UserRepository(dataStore).clearUserInfo()
            }
        }
    }
}
