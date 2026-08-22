package com.example.proyectoalkewallet.view.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectoalkewallet.R
import com.example.proyectoalkewallet.view.wallet.Home

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val actionLogin = findViewById<View>(R.id.btnLogin)
        val actionRegister = findViewById<View>(R.id.tvRegister)

        actionLogin.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        actionRegister.setOnClickListener {
            val intent = Intent(this, Singup::class.java)
            startActivity(intent)
        }
    }

}
