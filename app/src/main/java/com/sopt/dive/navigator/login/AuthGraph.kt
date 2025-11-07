package com.sopt.dive.navigator.login

import androidx.compose.material3.SnackbarHostState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.dive.data.UserRepository
import com.sopt.dive.navigator.AuthGraph
import com.sopt.dive.navigator.Login
import com.sopt.dive.navigator.SignUp
import com.sopt.dive.screen.LoginScreen
import com.sopt.dive.screen.SignUpScreen
import com.sopt.dive.viewModel.LoginViewModel
import com.sopt.dive.viewModel.SignUpViewModel

fun NavGraphBuilder.authGraph(navController: NavHostController, dataStore: DataStore<Preferences>,
                              snackbarHostState: SnackbarHostState) {
    navigation<AuthGraph>(
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                navController = navController,
                // refactor need: ViewModel 직접 주입은 LifeCycle 을 관리해주지 못함
                viewModel = LoginViewModel(userRepository = UserRepository(dataStore = dataStore)))
        }
        composable<SignUp> {
            SignUpScreen(
                navController = navController,
                viewModel = SignUpViewModel(userRepository = UserRepository(dataStore = dataStore)),
                snackbarHostState = snackbarHostState
            )
        }
    }
}