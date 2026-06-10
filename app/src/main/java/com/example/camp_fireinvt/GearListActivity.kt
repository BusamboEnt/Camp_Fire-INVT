package com.example.camp_fireinvt

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GearListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gear_list)

        findViewById<Button>(R.id.btn_list_back).setOnClickListener {
            // Return to main dashboard
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh gear list display
        displayGearItems()
    }

    private fun displayGearItems() {
        val container = findViewById<LinearLayout>(R.id.list_container)
        // Clear existing list items
        container.removeAllViews()
        
        val inflater = LayoutInflater.from(this)
        
        // Loop through parallel arrays
        for (i in 0 until GearManager.items.size) {
            // Inflate custom item layout
            val itemView = inflater.inflate(R.layout.view_gear_item, container, false)

            // Find views within card
            val nameQty = itemView.findViewById<TextView>(R.id.item_name_qty)
            val category = itemView.findViewById<TextView>(R.id.item_category)
            val notes = itemView.findViewById<TextView>(R.id.item_notes)
            val editBtn = itemView.findViewById<Button>(R.id.btn_edit_item)

            // Set item display data
            nameQty.text = "${GearManager.items[i]} (x${GearManager.quantities[i]})"
            category.text = "Category: ${GearManager.categories[i]}"
            notes.text = "Tip: ${GearManager.notes[i]}"

            // Handle item edit request
            editBtn.setOnClickListener {
                val intent = Intent(this, GearActivity::class.java)
                // Pass selected item index
                intent.putExtra("ITEM_INDEX", i)
                startActivity(intent)
            }

            // Add card to container
            container.addView(itemView)
        }
    }
}
