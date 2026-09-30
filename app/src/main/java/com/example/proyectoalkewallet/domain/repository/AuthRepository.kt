package com.example.proyectoalkewallet.domain.repository

import com.example.proyectoalkewallet.domain.model.User

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String,
    ): Result<User>

    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<User>

    suspend fun getCurrentUser(): User?

    suspend fun logout()
}

class UserNotFoundException : Exception()
