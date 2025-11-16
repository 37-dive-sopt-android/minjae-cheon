package com.sopt.dive.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String
)

sealed class LoginResult {
    data class Success(val data: LoginSuccessResponse) : LoginResult()
    data class Error(val error: ApiError) : LoginResult()
}

@Serializable
data class LoginSuccessResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: LoginSuccess
)

@Serializable
data class LoginSuccess(
    @SerialName("userId")
    val userId: Int,
    @SerialName("message")
    val msg: String
)

