package com.example.proyectoalkewallet.view.wallet

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalkewallet.R
import com.example.proyectoalkewallet.controller.AccountController
import com.example.proyectoalkewallet.databinding.ActivitySendMoneyBinding

class SendMoney : AppCompatActivity() {

    private lateinit var binding: ActivitySendMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accountController = AccountController()

        binding.btnSendMoney.setOnClickListener {
            val amountText = binding.etAmount.text?.toString().orEmpty()

            if (accountController.withdraw(amountText)) {
                Toast.makeText(this, R.string.retiro_exitoso, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, R.string.saldo_insuficiente, Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }
    }
}
