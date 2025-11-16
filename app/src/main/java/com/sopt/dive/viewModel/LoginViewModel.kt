package com.sopt.dive.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.UserDataStore
import com.sopt.dive.data.api.UserRepository
import com.sopt.dive.data.api.dto.LoginRequest
import com.sopt.dive.data.api.dto.LoginResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object IDLE: LoginUiState()
    data class Success(val uuid: Int) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
class LoginViewModel(
    val dataStore: UserDataStore
): ViewModel() {
    private val _loginStatus = MutableStateFlow<LoginUiState>(LoginUiState.IDLE)
    val loginStatus: StateFlow<LoginUiState> = _loginStatus.asStateFlow()

    fun login(userName: String, pw: String) {
        viewModelScope.launch {
            val res = UserRepository.loginApi(
                req = LoginRequest(
                    userName = userName,
                    password = pw
                )
            )
            when(res) {
                is LoginResult.Success -> {
                    dataStore.setCurrentUserUUID(res.data.data.userId)
                    _loginStatus.value = LoginUiState.Success(
                        uuid = res.data.data.userId
                    )
                }
                is LoginResult.Error -> {
                    _loginStatus.value = LoginUiState.Error(
                        message = res.error.code + ": " + res.error.message
                    )
                }
            }
        }
    }
}