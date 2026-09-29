package com.example.proyectoalkewallet.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalkewallet.databinding.ActivityLoginSingupBinding

class LoginSingup : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSingupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginSingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnActionSignup.setOnClickListener {
            val intent = Intent(this, Singup::class.java)
            startActivity(intent)
        }

        binding.tvActionLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
