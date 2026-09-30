package com.example.proyectoalkewallet.domain.usecase

import com.example.proyectoalkewallet.domain.repository.AccountRepository

class WithdrawMoneyUseCase(
    private val accountRepository: AccountRepository,
) {

    suspend operator fun invoke(amountText: String?): Boolean {
        val amount = amountText.toWalletAmount() ?: return false
        return accountRepository.withdraw(amount)
    }
}
