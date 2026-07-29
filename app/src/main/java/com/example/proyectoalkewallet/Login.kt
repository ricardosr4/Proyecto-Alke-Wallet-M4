package com.example.proyectoalkewallet

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val actionLogin = findViewById<View>(R.id.btnLogin)

        actionLogin.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
    }

}
