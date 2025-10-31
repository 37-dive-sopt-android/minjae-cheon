package com.sopt.dive.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigator.AppNavHost
import com.sopt.dive.ui.theme.DiveTheme

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

                AppNavHost(navController = navController,
                    dataStore = dataStore,
                    modifier = Modifier)
            }
        }
    }
}
