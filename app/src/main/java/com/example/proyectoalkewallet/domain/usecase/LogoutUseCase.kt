package com.example.proyectoalkewallet.domain.usecase

import com.example.proyectoalkewallet.domain.repository.AuthRepository

class LogoutUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke() {
        authRepository.logout()
    }
}
