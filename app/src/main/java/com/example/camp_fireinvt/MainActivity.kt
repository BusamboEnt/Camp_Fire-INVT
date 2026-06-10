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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loadingOverlay = findViewById<View>(R.id.loading_overlay)

        // Simulate lazy loading delay
        Handler(Looper.getMainLooper()).postDelayed({
            loadingOverlay.visibility = View.GONE
        }, 3000)

        findViewById<Button>(R.id.btn_gear).setOnClickListener {
            // Navigate to gear screen
            val intent = Intent(this, GearActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_view_list).setOnClickListener {
            // Navigate to list screen
            val intent = Intent(this, GearListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh items packed count
        updateItemCount()
    }

    private fun updateItemCount() {
        val totalItemsText = findViewById<TextView>(R.id.text_total_items)
        
        // Calculate packed item total
        var total = 0
        for (qty in GearManager.quantities) {
            total += qty
        }
        
        // Display total items packed
        totalItemsText.text = "Total Items Packed: $total"
    }
}
