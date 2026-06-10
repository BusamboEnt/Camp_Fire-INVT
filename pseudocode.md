# Campsite Commander Pseudocode

// START APP ON SPLASH
Display solid mountain logo
Wait for 1.5 seconds
Load saved inventory data
Go to main screen

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
