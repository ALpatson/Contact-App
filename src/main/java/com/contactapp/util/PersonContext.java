/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contactapp.util;
import com.contactapp.model.Person;

public class PersonContext {
    private static Person selectedPerson;
    
    public static Person getSelectedPerson() { return selectedPerson; }
    public static void setSelectedPerson(Person p) { selectedPerson = p; }
}