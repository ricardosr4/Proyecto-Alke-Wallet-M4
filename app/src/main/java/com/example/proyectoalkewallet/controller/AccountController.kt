package com.example.proyectoalkewallet.controller

import com.example.proyectoalkewallet.model.Account

class AccountController {

    fun getBalance(): Int = Account.getBalance()

    fun deposit(amountText: String?): Boolean {
        val amount = parseAmount(amountText) ?: return false
        return Account.deposit(amount)
    }

    fun withdraw(amountText: String?): Boolean {
        val amount = parseAmount(amountText) ?: return false
        return Account.withdraw(amount)
    }

    private fun parseAmount(amountText: String?): Double? {
        val normalizedAmount = amountText
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?.replace(',', '.')

        return normalizedAmount?.toDoubleOrNull()
    }
}
