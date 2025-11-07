package com.sopt.dive.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sopt.dive.navigator.HomePage
import com.sopt.dive.navigator.Route
import com.sopt.dive.navigator.SignUp
import com.sopt.dive.page.LoginPage
import com.sopt.dive.viewModel.LoginUiState
import com.sopt.dive.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val loginState by viewModel.loginStatus.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(loginState) {
        when(loginState) {
            is LoginUiState.Success -> {
                navController.navigate(HomePage)
            }
            is LoginUiState.Error -> {
                Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
            is  LoginUiState.IDLE -> { }
        }
    }

    LoginPage(modifier = Modifier.fillMaxSize(),
        onLoginClick = {id, pw -> viewModel.validate(id, pw) },
        onSignUpClick = { navController.navigate(SignUp) }
        )
}