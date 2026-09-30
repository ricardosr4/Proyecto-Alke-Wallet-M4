package com.example.proyectoalkewallet.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectoalkewallet.R
import com.example.proyectoalkewallet.databinding.ActivitySingupBinding
import com.example.proyectoalkewallet.view.wallet.Home
import com.example.proyectoalkewallet.viewmodel.AuthError
import com.example.proyectoalkewallet.viewmodel.AuthViewModel

class Singup : AppCompatActivity() {

    private lateinit var binding: ActivitySingupBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegister.setOnClickListener {
            register()
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun register() {
        clearErrors()

        val firstName = binding.etNombre.text?.toString()?.trim().orEmpty()
        val lastName = binding.etApellido.text?.toString()?.trim().orEmpty()
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val password = binding.etPassword.text?.toString().orEmpty()
        val repeatedPassword = binding.etRepeatPassword.text?.toString().orEmpty()

        when {
            firstName.isBlank() -> binding.tilNombre.error = getString(R.string.campo_obligatorio)
            lastName.isBlank() -> binding.tilApellido.error = getString(R.string.campo_obligatorio)
            email.isBlank() -> binding.tilEmail.error = getString(R.string.campo_obligatorio)
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tilEmail.error = getString(R.string.correo_invalido)
            }

            password.isBlank() -> binding.tilPassword.error = getString(R.string.campo_obligatorio)
            repeatedPassword.isBlank() -> {
                binding.tilRepeatPassword.error = getString(R.string.campo_obligatorio)
            }

            password != repeatedPassword -> {
                binding.tilRepeatPassword.error = getString(R.string.contrasenas_no_coinciden)
            }

            else -> sendRegistration(firstName, lastName, email, password)
        }
    }

    private fun sendRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ) {
        viewModel.register(firstName, lastName, email, password)
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            setLoading(state.isLoading)

            state.user?.let {
                viewModel.clearResult()
                startActivity(Intent(this, Home::class.java))
                finish()
            }

            if (state.error == AuthError.REGISTER_FAILED) {
                showRegistrationError()
                viewModel.clearResult()
            }
        }
    }

    private fun clearErrors() {
        binding.tilNombre.error = null
        binding.tilApellido.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilRepeatPassword.error = null
    }

    private fun setLoading(isLoading: Boolean) {
        binding.btnRegister.isEnabled = !isLoading
        binding.etNombre.isEnabled = !isLoading
        binding.etApellido.isEnabled = !isLoading
        binding.etEmail.isEnabled = !isLoading
        binding.etPassword.isEnabled = !isLoading
        binding.etRepeatPassword.isEnabled = !isLoading
    }

    private fun showRegistrationError() {
        Toast.makeText(this, R.string.error_registro, Toast.LENGTH_SHORT).show()
    }
}
