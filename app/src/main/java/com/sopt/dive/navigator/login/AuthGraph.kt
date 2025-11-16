package com.sopt.dive.navigator.login

import androidx.compose.material3.SnackbarHostState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.dive.navigator.AuthGraph
import com.sopt.dive.navigator.Login
import com.sopt.dive.navigator.SignUp
import com.sopt.dive.screen.LoginScreen
import com.sopt.dive.screen.SignUpScreen

fun NavGraphBuilder.authGraph(navController: NavHostController,
                              snackbarHostState: SnackbarHostState) {
    navigation<AuthGraph>(
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                navController = navController
            )
        }
        composable<SignUp> {
            SignUpScreen(
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }
    }
}