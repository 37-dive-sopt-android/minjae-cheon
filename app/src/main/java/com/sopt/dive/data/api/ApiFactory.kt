package com.sopt.dive.data.api

import com.sopt.dive.BuildConfig
import com.sopt.dive.data.api.dto.LoginRequest
import com.sopt.dive.data.api.dto.LoginSuccess
import com.sopt.dive.data.api.dto.LoginSuccessResponse
import com.sopt.dive.data.api.dto.SignUpRequest
import com.sopt.dive.data.api.dto.SignUpSuccess
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface UserService {
    @POST("/api/v1/users")
    suspend fun signUp(@Body req: SignUpRequest): Response<SignUpSuccess>
    @POST("/api/v1/auth/login")
    suspend fun login(@Body req: LoginRequest): Response<LoginSuccessResponse>
}

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.BASE_URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(
                "application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ServicePool {
    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }
}