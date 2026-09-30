package com.example.proyectoalkewallet.view.wallet

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalkewallet.R
import com.example.proyectoalkewallet.databinding.ActivityHomeBinding
import com.example.proyectoalkewallet.view.profile.Profile
import com.example.proyectoalkewallet.viewmodel.HomeViewModel
import java.util.Locale

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()

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
        viewModel.loadData()
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            state.user?.let { user ->
                binding.tvGreeting.text = getString(R.string.hola_usuario, user.firstName)
            }

            binding.tvBalance.text = String.format(
                Locale.forLanguageTag("es-CL"),
                getString(R.string.formato_saldo),
                state.balance,
            )
        }
    }
}
