package com.example.proyectoalkewallet.data.remote

import com.example.proyectoalkewallet.data.remote.dto.ApiUserDto
import com.example.proyectoalkewallet.data.remote.dto.AuthResponseDto
import com.example.proyectoalkewallet.data.remote.dto.LoginRequestDto
import com.example.proyectoalkewallet.data.remote.dto.RegisterRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlkeWalletApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): Response<AuthResponseDto>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequestDto): Response<AuthResponseDto>

    @GET("users")
    suspend fun getUsers(): Response<List<ApiUserDto>>

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): Response<ApiUserDto>
}
