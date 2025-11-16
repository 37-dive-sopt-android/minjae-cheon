package com.sopt.dive.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UserInfoStatus {
    data class Ready(val value: UserInfo): UserInfoStatus()
    data object NeedPending: UserInfoStatus()
}

class MainViewModel(
): ViewModel() {
    private val _userInfo = MutableStateFlow<UserInfoStatus>(UserInfoStatus.NeedPending)
    val userInfo = _userInfo.asStateFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            _userInfo.value = UserInfoStatus.Ready(
                TODO()
            )
        }
    }
}