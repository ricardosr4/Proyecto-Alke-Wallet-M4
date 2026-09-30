package com.example.proyectoalkewallet.domain.usecase

import com.example.proyectoalkewallet.domain.repository.AccountRepository

class GetBalanceUseCase(
    private val accountRepository: AccountRepository,
) {

    suspend operator fun invoke(): Int = accountRepository.getBalance()
}
