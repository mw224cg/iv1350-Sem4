/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;

/**
 * Thrown when Item ID is not found in the inventory.
 * 
 */
public class ItemNotFoundException extends Exception{
    private final int missingItemID;
    
    public ItemNotFoundException(int itemID){
        super("Item with Item ID: " + itemID + " not found in the inventory");
        this.missingItemID = itemID;
    }
    
}
