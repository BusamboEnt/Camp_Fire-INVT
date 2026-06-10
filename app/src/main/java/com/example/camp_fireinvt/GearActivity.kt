package com.example.camp_fireinvt

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GearActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gear)

        val nameInput = findViewById<EditText>(R.id.edit_gear_name)
        val categoryInput = findViewById<EditText>(R.id.edit_gear_category)
        val quantityInput = findViewById<EditText>(R.id.edit_gear_quantity)
        val notesInput = findViewById<EditText>(R.id.edit_gear_notes)

        findViewById<Button>(R.id.btn_save_gear).setOnClickListener {
            // Validates user input fields
            val name = nameInput.text.toString()
            val category = categoryInput.text.toString()
            val qtyStr = quantityInput.text.toString()
            val note = notesInput.text.toString()

            if (name.isNotEmpty() && category.isNotEmpty() && qtyStr.isNotEmpty()) {
                // Save to shared manager
                GearManager.items.add(name)
                GearManager.categories.add(category)
                GearManager.quantities.add(qtyStr.toInt())
                GearManager.notes.add(note)

                // Save to persistent storage
                GearManager.saveData(this)

                // Clear input field values
                nameInput.text.clear()
                categoryInput.text.clear()
                quantityInput.text.clear()
                notesInput.text.clear()

                Toast.makeText(this, "Gear Item Saved", Toast.LENGTH_SHORT).show()
            } else {
                // Shows input error message
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btn_gear_back).setOnClickListener {
            // Returns to main dashboard
            finish()
        }
    }
}
