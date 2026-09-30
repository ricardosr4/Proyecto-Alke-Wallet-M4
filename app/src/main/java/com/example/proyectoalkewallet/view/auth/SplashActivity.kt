package com.example.proyectoalkewallet.view.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.doOnPreDraw
import com.example.proyectoalkewallet.databinding.ActivitySplashBinding
import com.example.proyectoalkewallet.view.wallet.Home
import com.example.proyectoalkewallet.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        binding.main.doOnPreDraw {
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.checkSession()
            }, SPLASH_DURATION_MS)
        }
    }

    private fun observeViewModel() {
        viewModel.hasActiveSession.observe(this) { hasActiveSession ->
            val destination = if (hasActiveSession) Home::class.java else LoginSingup::class.java
            startActivity(Intent(this, destination))
            finish()
        }
    }

    companion object {
        private const val SPLASH_DURATION_MS = 3000L
    }
}
