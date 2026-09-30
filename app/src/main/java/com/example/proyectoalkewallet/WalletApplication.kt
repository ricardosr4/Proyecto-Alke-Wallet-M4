package com.example.proyectoalkewallet

import android.app.Application
import com.example.proyectoalkewallet.di.AppContainer

class WalletApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppContainer.initialize(this)
    }
}
