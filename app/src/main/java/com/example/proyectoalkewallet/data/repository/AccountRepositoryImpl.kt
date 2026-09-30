package com.example.proyectoalkewallet.data.repository

import com.example.proyectoalkewallet.data.local.database.UserDao
import com.example.proyectoalkewallet.data.local.preferences.SessionPreferences
import com.example.proyectoalkewallet.domain.repository.AccountRepository

class AccountRepositoryImpl(
    private val userDao: UserDao,
    private val sessionPreferences: SessionPreferences,
) : AccountRepository {

    override suspend fun getBalance(): Int = getCurrentUser()?.balance ?: DEFAULT_BALANCE

    override suspend fun deposit(amount: Double): Boolean {
        if (amount <= 0) return false

        val user = getCurrentUser() ?: return false
        userDao.updateBalance(user.id, user.balance + amount.toInt())
        return true
    }

    override suspend fun withdraw(amount: Double): Boolean {
        val user = getCurrentUser() ?: return false
        if (amount <= 0 || amount > user.balance) return false

        userDao.updateBalance(user.id, user.balance - amount.toInt())
        return true
    }

    private suspend fun getCurrentUser() = sessionPreferences.getCurrentUserId()?.let { userId ->
        userDao.getUserById(userId)
    }

    private companion object {
        const val DEFAULT_BALANCE = 124000
    }
}
