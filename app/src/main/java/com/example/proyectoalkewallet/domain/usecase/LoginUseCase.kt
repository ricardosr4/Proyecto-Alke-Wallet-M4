package com.example.proyectoalkewallet.domain.usecase

import com.example.proyectoalkewallet.domain.model.User
import com.example.proyectoalkewallet.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(
        email: String,
        password: String,
    ): Result<User> = authRepository.login(email, password)
}
