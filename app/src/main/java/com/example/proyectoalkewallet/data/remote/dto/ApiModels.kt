package com.example.proyectoalkewallet.data.remote.dto

data class ApiUserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val balance: Int,
)

data class LoginRequestDto(
    val email: String,
    val password: String,
)

data class RegisterRequestDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)

data class AuthResponseDto(
    val user: ApiUserDto,
)
