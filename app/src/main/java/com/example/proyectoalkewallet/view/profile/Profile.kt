package com.example.proyectoalkewallet.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalkewallet.R
import com.example.proyectoalkewallet.databinding.ActivityProfileBinding
import com.example.proyectoalkewallet.view.auth.LoginSingup
import com.example.proyectoalkewallet.viewmodel.ProfileViewModel

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        viewModel.loadUser()

        binding.cardLogout.setOnClickListener {
            binding.cardLogout.isEnabled = false
            viewModel.logout()
        }
    }

    private fun observeViewModel() {
        viewModel.user.observe(this) { user ->
            user ?: return@observe
            binding.tvProfileName.text = getString(
                R.string.nombre_completo,
                user.firstName,
                user.lastName,
            )
            binding.tvProfileEmail.text = user.email
        }

        viewModel.logoutComplete.observe(this) { logoutComplete ->
            if (logoutComplete) {
                val intent = Intent(this, LoginSingup::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
            }
        }
    }
}
