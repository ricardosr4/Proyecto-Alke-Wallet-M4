package com.example.proyectoalkewallet.view.wallet

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalkewallet.R
import com.example.proyectoalkewallet.controller.AccountController
import com.example.proyectoalkewallet.databinding.ActivityHomeBinding
import com.example.proyectoalkewallet.view.profile.Profile
import java.util.Locale

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val accountController = AccountController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            startActivity(Intent(this, SendMoney::class.java))
        }

        binding.btnReceive.setOnClickListener {
            startActivity(Intent(this, RequestMoney::class.java))
        }

        binding.ivAvatar.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        binding.tvBalance.text = String.format(
            Locale("es", "CL"),
            getString(R.string.formato_saldo),
            accountController.getBalance(),
        )
    }
}
