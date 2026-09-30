package com.example.proyectoalkewallet.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalkewallet.R
import com.example.proyectoalkewallet.databinding.ActivityLoginBinding
import com.example.proyectoalkewallet.view.wallet.Home
import com.example.proyectoalkewallet.viewmodel.AuthError
import com.example.proyectoalkewallet.viewmodel.AuthViewModel

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, Singup::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        binding.tilEmail.error = null
        binding.tilPassword.error = null

        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val password = binding.etPassword.text?.toString().orEmpty()

        when {
            email.isBlank() -> {
                binding.tilEmail.error = getString(R.string.campo_obligatorio)
                return
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tilEmail.error = getString(R.string.correo_invalido)
                return
            }

            password.isBlank() -> {
                binding.tilPassword.error = getString(R.string.campo_obligatorio)
                return
            }
        }

        viewModel.login(email, password)
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            setLoading(state.isLoading)

            state.user?.let {
                viewModel.clearResult()
                startActivity(Intent(this, Home::class.java))
                finish()
            }

            when (state.error) {
                AuthError.USER_NOT_FOUND -> {
                    binding.tilEmail.error = getString(R.string.usuario_no_encontrado)
                    viewModel.clearResult()
                }

                AuthError.LOGIN_FAILED -> {
                    showLoginError()
                    viewModel.clearResult()
                }

                else -> Unit
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.btnLogin.isEnabled = !isLoading
        binding.etEmail.isEnabled = !isLoading
        binding.etPassword.isEnabled = !isLoading
    }

    private fun showLoginError() {
        Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show()
    }
}
