package com.sopt.dive.data.api

import android.util.Log
import com.sopt.dive.data.api.dto.ErrorResponse
import com.sopt.dive.data.api.dto.SignUpError
import com.sopt.dive.data.api.dto.SignUpRequest
import com.sopt.dive.data.api.dto.SignUpResult

object UserRepository {

    suspend fun signUpApi(req: SignUpRequest): SignUpResult {
        val userService = ServicePool.userService

        Log.d("personal", req.toString())

        val res = userService.signUp(req)

        Log.d("personal", res.message())

        return when {
            res.isSuccessful -> {
                res.body()?.let {
                    SignUpResult.Success(
                        data = it
                    )
                } ?: SignUpResult.Error(
                    error = SignUpError(
                        success = false,
                        code = "UNKNOWN ERROR",
                        message = "some error",
                        data = ErrorResponse(
                            code = "UNKNOWN ERROR",
                            message = "I Don't Know!",
                            errors = listOf()
                        )
                    )
                )
            }
            else -> {
                res.errorBody().let {
                    when {
                        res.code() == 409 -> {
                            SignUpResult.Error(
                                error = SignUpError(
                                    success = false,
                                    code = "DUPLICATED_USERNAME",
                                    message = "이미 존재하는 사용자명입니다.",
                                    data = ErrorResponse(
                                        code = "VALIDATION_ERROR",
                                        message = "요청 값이 유효하지 않습니다.",
                                        errors = listOf()
                                    )
                                )
                            )
                        }
                        else -> {
                            SignUpResult.Error(
                                error = SignUpError(
                                success = false,
                                code = res.code().toString(),
                                message = res.errorBody().toString(),
                                data = ErrorResponse(
                                    code = "UNKNOWN",
                                    message = "UNKNOWN ERROR",
                                    errors = listOf()
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}