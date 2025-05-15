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
    
    /**
     * Creates a new instance with a message specifying 
     * the itemID that is not in the inventory.
     * @param missingItemID        The itemID not found in the inventory.
     */
    public ItemNotFoundException(int missingItemID){
        super("Item with Item ID: " + missingItemID+ " not found in the inventory");
        this.missingItemID = missingItemID;
    }

    /**
     * 
     * @return      The itemID not found in the inventory.
     */
    public int getMissingItemID(){
        return this.missingItemID;
    }
}
