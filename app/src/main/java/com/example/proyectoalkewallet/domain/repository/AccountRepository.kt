package com.example.proyectoalkewallet.domain.repository

interface AccountRepository {

    suspend fun getBalance(): Int

    suspend fun deposit(amount: Double): Boolean

    suspend fun withdraw(amount: Double): Boolean
}
