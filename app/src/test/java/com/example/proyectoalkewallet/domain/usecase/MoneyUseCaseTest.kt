package com.example.proyectoalkewallet.domain.usecase

import com.example.proyectoalkewallet.domain.repository.AccountRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MoneyUseCaseTest {

    @Test
    fun depositAndWithdrawPreserveAccountRules() = runBlocking {
        val repository = FakeAccountRepository()
        val depositMoney = DepositMoneyUseCase(repository)
        val withdrawMoney = WithdrawMoneyUseCase(repository)
        val initialBalance = repository.getBalance()

        assertFalse(depositMoney(null))
        assertFalse(depositMoney(" "))
        assertFalse(depositMoney("invalid"))
        assertFalse(depositMoney("-10"))
        assertEquals(initialBalance, repository.getBalance())

        assertTrue(depositMoney("10,50"))
        assertEquals(initialBalance + 10, repository.getBalance())

        assertTrue(withdrawMoney("5.25"))
        assertEquals(initialBalance + 5, repository.getBalance())

        assertFalse(withdrawMoney((repository.getBalance() + 1).toString()))
        assertEquals(initialBalance + 5, repository.getBalance())
    }
}

private class FakeAccountRepository : AccountRepository {

    private var balance = 124000

    override suspend fun getBalance(): Int = balance

    override suspend fun deposit(amount: Double): Boolean {
        if (amount <= 0) return false

        balance += amount.toInt()
        return true
    }

    override suspend fun withdraw(amount: Double): Boolean {
        if (amount <= 0 || amount > balance) return false

        balance -= amount.toInt()
        return true
    }
}
