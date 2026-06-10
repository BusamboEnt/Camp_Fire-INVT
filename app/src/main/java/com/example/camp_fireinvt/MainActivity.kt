package com.example.camp_fireinvt

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.view.View
import android.os.Handler
import android.os.Looper
import android.graphics.BitmapFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loadingOverlay = findViewById<View>(R.id.loading_overlay)
        val loadingLogo = findViewById<ImageView>(R.id.loading_logo)

        // Load logo from assets
        try {
            val inputStream = assets.open("Campfire Site.png")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            loadingLogo.setImageBitmap(bitmap)
        } catch (e: Exception) {
            // Fallback to vector drawable
            loadingLogo.setImageResource(R.drawable.ic_mountain)
        }

        // Simulate lazy loading delay
        Handler(Looper.getMainLooper()).postDelayed({
            loadingOverlay.visibility = View.GONE
        }, 3000)

        findViewById<Button>(R.id.btn_gear).setOnClickListener {
            // Open camping gear inventory
            Toast.makeText(this, "Opening Gear Inventory", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btn_food).setOnClickListener {
            // Open food supply inventory
            Toast.makeText(this, "Opening Food Supply", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btn_checklist).setOnClickListener {
            // Open complete packing checklist
            Toast.makeText(this, "Opening Complete Checklist", Toast.LENGTH_SHORT).show()
        }
    }
}
