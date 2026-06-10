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

import android.widget.TextView
import android.content.Intent

class MainActivity : AppCompatActivity() {

    // Storage for gear items
    private val gearQuantities = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loadingOverlay = findViewById<View>(R.id.loading_overlay)
        val totalItemsText = findViewById<TextView>(R.id.text_total_items)

        // Calculate packed item total
        var total = 0
        for (qty in gearQuantities) {
            total += qty
        }
        totalItemsText.text = "Total Items Packed: $total"

        // Simulate lazy loading delay
        Handler(Looper.getMainLooper()).postDelayed({
            loadingOverlay.visibility = View.GONE
        }, 3000)

        findViewById<Button>(R.id.btn_gear).setOnClickListener {
            // Navigate to gear screen
            val intent = Intent(this, GearActivity::class.java)
            startActivity(intent)
        }
    }
}
