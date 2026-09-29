package com.example.proyectoalkewallet.view.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.doOnPreDraw
import com.example.proyectoalkewallet.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        binding.main.doOnPreDraw {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, LoginSingup::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_DURATION_MS)
        }
    }

    companion object {
        private const val SPLASH_DURATION_MS = 3000L
    }
}
