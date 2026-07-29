package com.example.proyectoalkewallet

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginSingup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_singup)

        val btnActionSignup = findViewById<View>(R.id.btn_action_signup)
        val tvActionLogin = findViewById<View>(R.id.tv_action_login)

        btnActionSignup.setOnClickListener {
            startActivity(Intent(this, Singup::class.java))
        }

        tvActionLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }
}
