package com.sopt.dive.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.UserInfo
import com.sopt.dive.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object IDLE: LoginUiState()
    data class Success(val userInfo: UserInfo) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
class LoginViewModel(
    val userRepository: UserRepository
): ViewModel() {
    private val _loginStatus = MutableStateFlow<LoginUiState>(LoginUiState.IDLE)
    val loginStatus: StateFlow<LoginUiState> = _loginStatus.asStateFlow()

    fun validate(id: String, pw: String) {
        viewModelScope.launch {
            val savedUser = userRepository.getCurrentUserInfo()
            if(savedUser.validate(id, pw)) {
                _loginStatus.value = LoginUiState.Success(savedUser)
            } else {
                _loginStatus.value = LoginUiState.Error("Wrong ID or PW")
            }
        }
    }
}