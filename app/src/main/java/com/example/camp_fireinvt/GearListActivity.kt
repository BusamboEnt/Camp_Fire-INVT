package com.example.camp_fireinvt

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class GearListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gear_list)

        // Populate the gear list
        displayGearItems()

        findViewById<Button>(R.id.btn_list_back).setOnClickListener {
            // Return to main dashboard
            finish()
        }
    }

    private fun displayGearItems() {
        val container = findViewById<LinearLayout>(R.id.list_container)
        
        // Loop through parallel arrays
        for (i in 0 until GearManager.items.size) {
            val itemView = LinearLayout(this)
            itemView.orientation = LinearLayout.VERTICAL
            itemView.setPadding(0, 0, 0, 32)

            // Item name and quantity
            val titleText = TextView(this)
            titleText.text = "${GearManager.items[i]} (x${GearManager.quantities[i]})"
            titleText.textSize = 20.sp
            titleText.setTextColor(ContextCompat.getColor(this, R.color.camp_primary_variant))
            titleText.setPadding(0, 0, 0, 4)
            itemView.addView(titleText)

            // Item category display label
            val categoryText = TextView(this)
            categoryText.text = "Category: ${GearManager.categories[i]}"
            categoryText.textSize = 14.sp
            categoryText.setTextColor(ContextCompat.getColor(this, R.color.camp_secondary))
            itemView.addView(categoryText)

            // Item notes and tips
            val noteText = TextView(this)
            noteText.text = "Tip: ${GearManager.notes[i]}"
            noteText.textSize = 14.sp
            noteText.setTextColor(ContextCompat.getColor(this, R.color.gray))
            noteText.setPadding(0, 4, 0, 0)
            itemView.addView(noteText)

            // Add view to container
            container.addView(itemView)
        }
    }
    
    // Helper extension for sp
    private val Int.sp: Float get() = this.toFloat()
}
