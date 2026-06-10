package com.example.camp_fireinvt

/**
 * Manages gear inventory data.
 */
object GearManager {
    // Parallel arrays for storage
    val items = ArrayList<String>()
    val categories = ArrayList<String>()
    val quantities = ArrayList<Int>()
    val notes = ArrayList<String>()

    init {
        // Initialize with sample data
        addSampleGear("Camping Tent", "Shelter", 1, "Waterproof four person tent")
        addSampleGear("Sleeping Bag", "Bedding", 2, "Rated for cold weather")
        addSampleGear("Portable Stove", "Cooking", 1, "Includes two fuel canisters")
    }

    private fun addSampleGear(name: String, cat: String, qty: Int, note: String) {
        // Add sample gear details
        items.add(name)
        categories.add(cat)
        quantities.add(qty)
        notes.add(note)
    }
}
