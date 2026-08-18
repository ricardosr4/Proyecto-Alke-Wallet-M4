package com.example.proyectoalkewallet.controller.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.doOnPreDraw
import com.example.proyectoalkewallet.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        findViewById<View>(R.id.main).doOnPreDraw {
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
