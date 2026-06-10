package com.example.camp_fireinvt

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Manages gear inventory data.
 */
object GearManager {
    // Parallel arrays for storage
    var items = ArrayList<String>()
    var categories = ArrayList<String>()
    var quantities = ArrayList<Int>()
    var notes = ArrayList<String>()

    // Storage preference file name
    private const val PREFS_NAME = "GearPrefs"
    
    /**
     * Load data from storage.
     */
    fun loadData(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()

        // Read items from preferences
        val itemsJson = prefs.getString("items", null)
        if (itemsJson != null) {
            val type = object : TypeToken<ArrayList<String>>() {}.type
            items = gson.fromJson(itemsJson, type)
        }

        // Read categories from preferences
        val categoriesJson = prefs.getString("categories", null)
        if (categoriesJson != null) {
            val type = object : TypeToken<ArrayList<String>>() {}.type
            categories = gson.fromJson(categoriesJson, type)
        }

        // Read quantities from preferences
        val quantitiesJson = prefs.getString("quantities", null)
        if (quantitiesJson != null) {
            val type = object : TypeToken<ArrayList<Int>>() {}.type
            quantities = gson.fromJson(quantitiesJson, type)
        }

        // Read notes from preferences
        val notesJson = prefs.getString("notes", null)
        if (notesJson != null) {
            val type = object : TypeToken<ArrayList<String>>() {}.type
            notes = gson.fromJson(notesJson, type)
        }

        // Add sample if empty
        if (items.isEmpty()) {
            addSampleData(context)
        }
    }

    /**
     * Save data to storage.
     */
    fun saveData(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = Gson()

        // Store arrays as JSON
        editor.putString("items", gson.toJson(items))
        editor.putString("categories", gson.toJson(categories))
        editor.putString("quantities", gson.toJson(quantities))
        editor.putString("notes", gson.toJson(notes))
        editor.apply()
    }

    private fun addSampleData(context: Context) {
        // Initialize with sample items
        items.add("Camping Tent")
        categories.add("Shelter & Sleep")
        quantities.add(1)
        notes.add("Waterproof four person tent")

        items.add("Sleeping Bag")
        categories.add("Shelter & Sleep")
        quantities.add(2)
        notes.add("Rated for cold weather")

        // Persist the sample data
        saveData(context)
    }
}
