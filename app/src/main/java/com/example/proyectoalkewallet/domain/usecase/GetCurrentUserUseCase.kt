package com.example.proyectoalkewallet.domain.usecase

import com.example.proyectoalkewallet.domain.model.User
import com.example.proyectoalkewallet.domain.repository.AuthRepository

class GetCurrentUserUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(): User? = authRepository.getCurrentUser()
}
