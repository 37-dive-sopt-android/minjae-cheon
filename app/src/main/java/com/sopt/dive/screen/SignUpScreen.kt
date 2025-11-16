package com.sopt.dive.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sopt.dive.UserUIInfo
import com.sopt.dive.page.SignUpPage
import com.sopt.dive.viewModel.SignUpState
import com.sopt.dive.viewModel.SignUpViewModel


@Composable
fun SignUpScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: SignUpViewModel = viewModel()
) {
    val signUpState by viewModel.signUpResult.collectAsStateWithLifecycle()

    val username by viewModel.username.collectAsStateWithLifecycle()
    val pw by viewModel.pw.collectAsStateWithLifecycle()
    val name by viewModel.name.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val age by viewModel.age.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(signUpState) {
        when(val state = signUpState) {
            is SignUpState.Success -> {
                Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
            SignUpState.Idle -> { }
            is SignUpState.Error -> {
                snackbarHostState.showSnackbar("회원가입 실패: ${state.msg}")
                viewModel.init()
            }
        }
    }

    SignUpPage(
        userInfoList = listOf(
            UserUIInfo(
                text = "USERNAME",
                label = "아이디를 입력해주세요",
                value = username,
                onChange = viewModel::onIdChange,
                inputVisibility = true
            ),
            UserUIInfo(
                text = "PW",
                label = "패스워드를 입력해주세요",
                value = pw,
                onChange = viewModel::onPwChange,
                inputVisibility = false
            ),
            UserUIInfo(
                text = "NAME",
                label = "이름을 입력해주세요",
                value = name,
                onChange = viewModel::onNameChange,
                inputVisibility = true
            ),
            UserUIInfo(
                text = "EMAIL",
                label = "이메일을 입력해주세요",
                value = email,
                onChange = viewModel::onEmailChange,
                inputVisibility = true
            ),
            UserUIInfo(
                text = "AGE",
                label = "나이를 입력해주세요",
                value = age,
                onChange = viewModel::onAgeChange,
                inputVisibility = true
            ),
        ),
        onSignUpClick = viewModel::signUp,
        modifier = Modifier
            .fillMaxSize()
    )
}
