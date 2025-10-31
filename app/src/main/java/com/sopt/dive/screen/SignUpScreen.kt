package com.sopt.dive.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sopt.dive.navigator.login.Login
import com.sopt.dive.page.SignUpPage
import com.sopt.dive.viewModel.SignUpState
import com.sopt.dive.viewModel.SignUpViewModel


@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel
) {
    val signUpState by viewModel.signUpResult.collectAsStateWithLifecycle()

    val id by viewModel.id.collectAsStateWithLifecycle()
    val pw by viewModel.pw.collectAsStateWithLifecycle()
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()
    val mbti by viewModel.mbti.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(signUpState) {
        when(val state = signUpState) {
            is SignUpState.Success -> {
                Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
            SignUpState.Idle -> { }
            is SignUpState.Error -> {
                snackbarHostState.showSnackbar("회원가입 실패: ${state.msg}")
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        SignUpPage(
            id = id,
            onIdChange = viewModel::onIdChange,
            pw = pw,
            onPwChange = viewModel::onPwChange,
            nickname = nickname,
            onNicknameChange = viewModel::onNicknameChange,
            mbti = mbti,
            onMbtiChange = viewModel::onMbtiChange,
            onSignUpClick = viewModel::signUp,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}
