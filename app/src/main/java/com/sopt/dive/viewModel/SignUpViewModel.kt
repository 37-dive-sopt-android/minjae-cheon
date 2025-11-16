package com.sopt.dive.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.UserInfo
import com.sopt.dive.data.api.UserRepository
import com.sopt.dive.data.api.dto.SignUpRequest
import com.sopt.dive.data.api.dto.SignUpResult
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
    NAME_FAULT("name 은 한 글자 이상, 공백으로만 이루어지면 안됩니다!"),
    EMAIL_FAULT("올바른 이메일 형식으로 작성해야합니다!")
}

class SignUpViewModel(
): ViewModel() {
    val username = MutableStateFlow("")
    val pw = MutableStateFlow("")
    val name = MutableStateFlow("")
    val email = MutableStateFlow("")
    val age = MutableStateFlow("")

    private val _signUpResult = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpResult = _signUpResult.asStateFlow()

    fun onIdChange(newUsername: String) { username.value = newUsername }
    fun onPwChange(newPw: String) { pw.value = newPw }
    fun onNameChange(newName: String) { name.value = newName }
    fun onEmailChange(newEmail: String) { email.value = newEmail }
    fun onAgeChange(newAge: String) { age.value = newAge }

    fun init() {
        _signUpResult.value = SignUpState.Idle
    }
    fun signUp() {
        val validate = validate(username.value, pw.value, name.value, email.value)
        if(validate != ValidateResult.SUCCESS) {
            _signUpResult.value = SignUpState.Error(
                msg = validate.msg
            )
            return
        }

        viewModelScope.launch {
            try {
                // signUp API
                val result = UserRepository.signUpApi(
                    req = SignUpRequest(
                        username = username.value,
                        password = pw.value,
                        name = name.value,
                        email = email.value,
                        age = age.value.toInt()
                    )
                )

                when(result) {
                    is SignUpResult.Error -> {
                        _signUpResult.value = SignUpState.Error(
                            msg = result.error.code + ": " + result.error.message
                        )
                    }
                    is SignUpResult.Success -> {
                        _signUpResult.value = SignUpState.Success(
                            UserInfo(
                                username = username.value,
                                pw = pw.value,
                                name = name.value,
                                email = email.value,
                                age = age.value.toInt()
                            )
                        )
                    }
                }
            } catch(e: Exception) {
                _signUpResult.value = SignUpState.Error(msg = "저장 중 오류가 발생했습니다: ${e.message}")
            }

        }

    }

    private fun validate(id: String, pw: String, name: String, email: String): ValidateResult {
        if(id.length !in 6..10) {
            return ValidateResult.ID_FAULT
        }
        if(pw.length !in 8..12) {
            return ValidateResult.PW_FAULT
        }
        if(name.trim().isEmpty()) {
            return ValidateResult.NAME_FAULT
        }
        if(! "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
                .matches(email)) {
            return ValidateResult.EMAIL_FAULT
        }
        return ValidateResult.SUCCESS
    }
}
