package com.example.proyectoalkewallet.di

import android.content.Context
import com.example.proyectoalkewallet.data.local.database.WalletDatabase
import com.example.proyectoalkewallet.data.local.preferences.SessionPreferences
import com.example.proyectoalkewallet.data.remote.ApiClient
import com.example.proyectoalkewallet.data.repository.AccountRepositoryImpl
import com.example.proyectoalkewallet.data.repository.AuthRepositoryImpl
import com.example.proyectoalkewallet.domain.repository.AccountRepository
import com.example.proyectoalkewallet.domain.repository.AuthRepository

object AppContainer {

    lateinit var accountRepository: AccountRepository
        private set

    lateinit var authRepository: AuthRepository
        private set

    fun initialize(context: Context) {
        val userDao = WalletDatabase.getInstance(context).userDao()
        val sessionPreferences = SessionPreferences(context.applicationContext)

        accountRepository = AccountRepositoryImpl(userDao, sessionPreferences)
        authRepository = AuthRepositoryImpl(
            api = ApiClient.service,
            userDao = userDao,
            sessionPreferences = sessionPreferences,
        )
    }
}
