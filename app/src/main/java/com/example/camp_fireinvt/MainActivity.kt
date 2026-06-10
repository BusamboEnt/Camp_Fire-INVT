package com.example.camp_fireinvt

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
