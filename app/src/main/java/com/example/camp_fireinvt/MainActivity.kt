package com.example.camp_fireinvt

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.view.View
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loadingOverlay = findViewById<View>(R.id.loading_overlay)
        val loadingLogo = findViewById<ImageView>(R.id.loading_logo)

        // Try to load Campfire Site.png from assets as the logo
        try {
            val inputStream = assets.open("Campfire Site.png")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            loadingLogo.setImageBitmap(bitmap)
        } catch (e: Exception) {
            // Fallback to vector drawable
            loadingLogo.setImageResource(R.drawable.ic_mountain)
        }

        // Simulate Lazy Loading for 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            loadingOverlay.visibility = View.GONE
        }, 3000)

        findViewById<Button>(R.id.btn_gear).setOnClickListener {
            // Intent to GearActivity (to be created)
            Toast.makeText(this, "Opening Gear Inventory", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btn_food).setOnClickListener {
            // Intent to FoodActivity (to be created)
            Toast.makeText(this, "Opening Food Supply", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btn_checklist).setOnClickListener {
            // Intent to ChecklistActivity (to be created)
            Toast.makeText(this, "Opening Complete Checklist", Toast.LENGTH_SHORT).show()
        }
    }
}
