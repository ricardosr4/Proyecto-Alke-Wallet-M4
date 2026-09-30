package com.example.proyectoalkewallet.domain.usecase

import com.example.proyectoalkewallet.domain.model.User
import com.example.proyectoalkewallet.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<User> = authRepository.register(firstName, lastName, email, password)
}
