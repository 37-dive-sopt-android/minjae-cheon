package com.sopt.dive.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.StringBuilder

@Serializable
data class SignUpRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int
) {
    override fun toString(): String = buildString {
        append("username: $username\n")
        append("password: $password\n")
        append("name: $name\n")
        append("email: $email\n")
        append("age: $age")
    }
}
sealed class SignUpResult {
    data class Success(val data: SignUpSuccess) : SignUpResult()
    data class Error(val error: ApiError) : SignUpResult()
}

@Serializable
data class SignUpSuccess(
    @SerialName("id")
    val id: Int,
    @SerialName("username")
    val username: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
    @SerialName("status")
    val status: String
)
@Serializable
data class ApiError(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ErrorResponse
)
@Serializable
data class ErrorResponse(
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("errors")
    val errors: List<FieldError>
)
@Serializable
data class FieldError(
    @SerialName("field")
    val field: String,
    @SerialName("value")
    val value: String,
    @SerialName("reason")
    val reason: String
)