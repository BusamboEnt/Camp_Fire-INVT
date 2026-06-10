package com.example.camp_fireinvt

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.os.Build
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

import android.graphics.BitmapFactory
import android.widget.ImageView
import java.io.IOException

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Initializes splash image view
        val splashImage = findViewById<ImageView>(R.id.splash_image)
        
        // Loads image from assets
        try {
            val inputStream = assets.open("camp mountain.png")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            splashImage.setImageBitmap(bitmap)
        } catch (e: IOException) {
            // Fallbacks to vector drawable
            splashImage.setImageResource(R.drawable.ic_mountain)
        }

        // Load persistent gear data
        GearManager.loadData(this)

        // Enable full screen mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // Transitions to next screen
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}
