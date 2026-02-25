package com.contactapp.util;

import com.contactapp.model.Person;

/**
 * Temporary storage for passing contact data between different windows.
 * When a user selects a contact to view or edit, this class holds that contact
 * so the detail/edit window can access it without reopening the database.
 */
public class PersonContext {
    
    // Stores the currently selected contact
    private static Person selectedPerson;
    
    /**
     * Get the contact that was selected by the user.
     * 
     * @return The selected Person, or null if no contact is selected
     */
    public static Person getSelectedPerson() { 
        return selectedPerson; 
    }
    
    /**
     * Store the contact that the user selected.
     * This is typically called before opening the edit or detail view.
     * 
     * @param p The contact to store
     */
    public static void setSelectedPerson(Person p) { 
        selectedPerson = p; 
    }
}