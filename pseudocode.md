# Campsite Commander Pseudocode

// START APP ON SPLASH
Display solid mountain logo
Wait for 1.5 seconds
Load saved inventory data
Go to main screen

FUNCTION init application():
    displaySplashScreen(SOLID_MOUNTAIN_LOGO)
    wait(1.5 seconds)
    
    // Load data from persistent storage
    GLOBAL inventoryData = loadSavedInventoryData() 
    
    navigateTo(MAIN_SCREEN)
END FUNCTION


// MAIN SCREEN DASHBOARD
Show semi-transparent loading text
Wait for 3 seconds
Hide the loading text
Loop through gear quantities:
    Add quantity to total
Display total items packed
Tapping "Add Gear" button:
    Go to gear form
Tapping "View Checklist" button:
    Go to gear list

FUNCTION showMainScreen():
    showLoadingText(opacity=0.5)
    wait(3.0 seconds)
    hideLoadingText()

    // Calculate total items
    DECLARE totalItems = 0
    FOR EACH gearItem IN inventoryData:
        totalItems = totalItems + gearItem.quantity
    END FOR
    
    displayTotalItems(totalItems)
END FUNCTION

// Event Listeners
ON CLICK "Add Gear" BUTTON:
    navigateTo(GEAR_FORM, mode="CREATE", itemIndex=NULL)

ON CLICK "View Checklist" BUTTON:
    navigateTo(GEAR_LIST)

// ADD GEAR FORM
Show item detail fields
If editing existing item:
    Fill fields with data
    Change button to "Update"
Tapping "Save" button:
    If all fields filled:
        If update mode:
            Update parallel array index
        Else:
            Add to parallel arrays
        Save arrays to storage
        Go back to dashboard

FUNCTION showGearForm(mode, itemIndex):
    displayFormFields()
    
    IF mode == "EDIT":
        // Populate fields with existing data
        field_name.value     = inventoryData.names[itemIndex]
        field_quantity.value = inventoryData.quantities[itemIndex]
        field_category.value = inventoryData.categories[itemIndex]
        
        submitButton.text = "Update"
    ELSE:
        submitButton.text = "Save"
    END IF
END FUNCTION

// Form Submission
ON CLICK submitButton:
    IF NOT allFieldsFilled():
        displayError("Please fill out all fields.")
        RETURN
    END IF

    // Collect data from form
    DECLARE newData = getFormData()

    IF mode == "EDIT":
        // Update parallel arrays at specific index
        inventoryData.names[itemIndex]      = newData.name
        inventoryData.quantities[itemIndex]  = newData.quantity
        inventoryData.categories[itemIndex]  = newData.category
    ELSE:
        // Append new data to parallel arrays
        APPEND newData.name TO inventoryData.names
        APPEND newData.quantity TO inventoryData.quantities
        APPEND newData.category TO inventoryData.categories
    END IF

    saveToStorage(inventoryData)
    navigateTo(MAIN_SCREEN)

// GEAR CHECKLIST VIEW
Display checklist header title
Clear current list items
Loop through parallel arrays:
    Create a gear card
    Set name and quantity
    Set category and tips
    Add card to list
Tapping "Edit" on card:
    Go to gear form
    Pass item index number
Tapping "Back" to dashboard:
    Go back to main

FUNCTION showGearList():
    displayHeader("Gear Checklist")
    clearUIListContainer()

    // Loop through parallel arrays using index
    FOR i FROM 0 TO LENGTH(inventoryData.names) - 1:
        DECLARE card = createGearCard()
        
        card.title    = inventoryData.names[i]
        card.quantity = inventoryData.quantities[i]
        card.category = inventoryData.categories[i]
        card.tips     = inventoryData.tips[i]
        
        // Attach the specific index to the card's edit button
        card.editButton.onClick = FUNCTION():
            navigateTo(GEAR_FORM, mode="EDIT", itemIndex=i)
        END FUNCTION

        addCardToUIContainer(card)
    END FOR
END FUNCTION

// Navigation Back
ON CLICK "Back to Dashboard" BUTTON:
    navigateTo(MAIN_SCREEN)

