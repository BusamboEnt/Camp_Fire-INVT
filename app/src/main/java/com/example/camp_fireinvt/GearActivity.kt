package com.example.camp_fireinvt

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GearActivity : AppCompatActivity() {
    
    // Track current edit index
    private var editIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gear)

        val nameInput = findViewById<EditText>(R.id.edit_gear_name)
        val categoryInput = findViewById<AutoCompleteTextView>(R.id.edit_gear_category)
        val quantityInput = findViewById<EditText>(R.id.edit_gear_quantity)
        val notesInput = findViewById<EditText>(R.id.edit_gear_notes)
        val saveBtn = findViewById<Button>(R.id.btn_save_gear)

        // Define gear category options
        val categories = arrayOf(
            "Shelter & Sleep",
            "Camp Kitchen & Food",
            "Clothing",
            "Toiletries & Health",
            "Tools, Safety & Comfort"
        )

        // Set up dropdown adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categories)
        categoryInput.setAdapter(adapter)

        // Check for edit mode
        editIndex = intent.getIntExtra("ITEM_INDEX", -1)
        
        if (editIndex != -1) {
            // Populate fields for editing
            nameInput.setText(GearManager.items[editIndex])
            categoryInput.setText(GearManager.categories[editIndex], false)
            quantityInput.setText(GearManager.quantities[editIndex].toString())
            notesInput.setText(GearManager.notes[editIndex])
            
            // Update the button text
            saveBtn.text = "Update Gear Item"
        }

        saveBtn.setOnClickListener {
            // Validate user input fields
            val name = nameInput.text.toString()
            val category = categoryInput.text.toString()
            val qtyStr = quantityInput.text.toString()
            val note = notesInput.text.toString()

            if (name.isNotEmpty() && category.isNotEmpty() && qtyStr.isNotEmpty()) {
                if (editIndex != -1) {
                    // Update existing item details
                    GearManager.items[editIndex] = name
                    GearManager.categories[editIndex] = category
                    GearManager.quantities[editIndex] = qtyStr.toInt()
                    GearManager.notes[editIndex] = note
                    Toast.makeText(this, "Gear Item Updated", Toast.LENGTH_SHORT).show()
                } else {
                    // Save to shared manager
                    GearManager.items.add(name)
                    GearManager.categories.add(category)
                    GearManager.quantities.add(qtyStr.toInt())
                    GearManager.notes.add(note)
                    Toast.makeText(this, "Gear Item Saved", Toast.LENGTH_SHORT).show()
                }

                // Save to persistent storage
                GearManager.saveData(this)
                // Close the activity screen
                finish()
            } else {
                // Show input error message
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btn_gear_back).setOnClickListener {
            // Return to previous screen
            finish()
        }
    }
}
