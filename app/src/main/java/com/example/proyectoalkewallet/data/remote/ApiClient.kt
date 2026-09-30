package com.example.proyectoalkewallet.data.remote

import com.example.proyectoalkewallet.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val service: AlkeWalletApi by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlkeWalletApi::class.java)
    }
}
