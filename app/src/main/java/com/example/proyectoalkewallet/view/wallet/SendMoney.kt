package com.example.proyectoalkewallet.view.wallet

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalkewallet.R
import com.example.proyectoalkewallet.databinding.ActivitySendMoneyBinding
import com.example.proyectoalkewallet.viewmodel.MoneyViewModel

class SendMoney : AppCompatActivity() {

    private lateinit var binding: ActivitySendMoneyBinding
    private val viewModel: MoneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()

        binding.btnSendMoney.setOnClickListener {
            val amountText = binding.etAmount.text?.toString().orEmpty()
            viewModel.withdraw(amountText)
        }

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            binding.btnSendMoney.isEnabled = !state.isLoading

            state.success?.let { success ->
                viewModel.clearResult()
                if (success) {
                    Toast.makeText(this, R.string.retiro_exitoso, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, R.string.saldo_insuficiente, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
