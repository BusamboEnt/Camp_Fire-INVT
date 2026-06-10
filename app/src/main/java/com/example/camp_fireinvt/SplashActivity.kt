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

        val splashImage = findViewById<ImageView>(R.id.splash_image)
        
        // Try to load camp mountain.png from assets
        try {
            val inputStream = assets.open("camp mountain.png")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            splashImage.setImageBitmap(bitmap)
        } catch (e: IOException) {
            // Fallback to vector drawable if asset is missing
            splashImage.setImageResource(R.drawable.ic_mountain)
        }

        // Make full screen
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

        // Transition to MainActivity after 1.5 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}
