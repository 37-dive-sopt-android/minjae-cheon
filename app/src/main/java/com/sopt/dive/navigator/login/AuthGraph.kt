package com.sopt.dive.navigator.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.dive.data.UserRepository
import com.sopt.dive.navigator.Auth
import com.sopt.dive.screen.LoginScreen
import com.sopt.dive.screen.SignUpScreen
import com.sopt.dive.viewModel.LoginViewModel
import com.sopt.dive.viewModel.SignUpViewModel
import kotlinx.serialization.Serializable

@Serializable data object Login
@Serializable data object SignUp

fun NavGraphBuilder.authGraph(navController: NavHostController, dataStore: DataStore<Preferences>) {
    navigation<Auth>(
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                navController = navController,
                viewModel = LoginViewModel(userRepository = UserRepository(dataStore = dataStore)))
        }
        composable<SignUp> {
            SignUpScreen(
                navController = navController,
                viewModel = SignUpViewModel(userRepository = UserRepository(dataStore = dataStore))
            )
        }
    }
}