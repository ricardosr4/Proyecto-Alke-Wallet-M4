package com.example.proyectoalkewallet.domain.usecase

import com.example.proyectoalkewallet.domain.repository.AccountRepository

class DepositMoneyUseCase(
    private val accountRepository: AccountRepository,
) {

    suspend operator fun invoke(amountText: String?): Boolean {
        val amount = amountText.toWalletAmount() ?: return false
        return accountRepository.deposit(amount)
    }
}

internal fun String?.toWalletAmount(): Double? {
    val normalizedAmount = this
        ?.trim()
        ?.takeIf { it.isNotEmpty() }
        ?.replace(',', '.')

    return normalizedAmount?.toDoubleOrNull()
}
