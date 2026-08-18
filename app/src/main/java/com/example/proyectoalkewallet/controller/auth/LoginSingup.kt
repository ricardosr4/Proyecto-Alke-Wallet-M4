package com.example.proyectoalkewallet.controller.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalkewallet.R

class LoginSingup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_singup)

        val btnActionSignup = findViewById<View>(R.id.btn_action_signup)
        val tvActionLogin = findViewById<View>(R.id.tv_action_login)

        btnActionSignup.setOnClickListener {
            val intent = Intent(this, Singup::class.java)
            startActivity(intent)
        }

        tvActionLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
