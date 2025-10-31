package com.sopt.dive.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.UserInfo
import com.sopt.dive.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SignUpState {
    object Idle : SignUpState()
    data class Success(val userInfo: UserInfo) : SignUpState()
    data class Error(val msg: String) : SignUpState()
}
enum class ValidateResult(val msg: String) {
    SUCCESS("success"),
    ID_FAULT("ID 는 6~10 글자여야 합니다!"),
    PW_FAULT("PW 는 8~12 글자여야 합니다!"),
    NICKNAME_FAULT("nickname 은 한 글자 이상, 공백으로만 이루어지면 안됩니다!"),
    MBTI_FAULT("MBTI 는 4글자 영어이어야 합니다!")
}

class SignUpViewModel(
    private val userRepository: UserRepository,
): ViewModel() {
    val id = MutableStateFlow("")
    val pw = MutableStateFlow("")
    val nickname = MutableStateFlow("")
    val mbti = MutableStateFlow("")

    private val _signUpResult = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpResult = _signUpResult.asStateFlow()

    fun onIdChange(newId: String) { id.value = newId }
    fun onPwChange(newPw: String) { pw.value = newPw }
    fun onNicknameChange(newNickname: String) { nickname.value = newNickname }
    fun onMbtiChange(newMbti: String) { mbti.value = newMbti }

    fun signUp() {
        val result = validate(id.value, pw.value, nickname.value, mbti.value)
        if(result != ValidateResult.SUCCESS) {
            _signUpResult.value = SignUpState.Error(
                msg = result.msg
            )
            return
        }
        viewModelScope.launch {
            try {
                userRepository.setCurrentUserInfo(
                    UserInfo(id.value, pw.value, nickname.value, mbti.value)
                )

                _signUpResult.value = SignUpState.Success(
                    UserInfo(id.value, pw.value, nickname.value, mbti.value)
                )
            } catch(e: Exception) {
                _signUpResult.value = SignUpState.Error(msg = "저장 중 오류가 발생했습니다: ${e.message}")
            }

        }

    }

    private fun validate(id: String, pw: String, nickname: String, mbti: String): ValidateResult {
        if(id.length !in 6..10) {
            return ValidateResult.ID_FAULT
        }
        if(pw.length !in 8..12) {
            return ValidateResult.PW_FAULT
        }
        if(nickname.trim().isEmpty()) {
            return ValidateResult.NICKNAME_FAULT
        }

        if(! "[a-zA-Z]{4}".toRegex().matches(mbti)) {
            return ValidateResult.MBTI_FAULT
        }
        return ValidateResult.SUCCESS
    }
}
