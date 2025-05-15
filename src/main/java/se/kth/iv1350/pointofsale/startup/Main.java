/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.startup;

import java.io.IOException;
import se.kth.iv1350.pointofsale.view.View;
import se.kth.iv1350.pointofsale.integration.ExternalInventorySystem;
import se.kth.iv1350.pointofsale.integration.ExternalAccountingSystem;
import se.kth.iv1350.pointofsale.integration.DiscountDatabase;
import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.integration.Printer;
        


/**
 * Containsthe<code>main</code>method. Performs all startup
 * of the program.
 *
 */
public class Main {
    /**
     * Starts the program.
     * @param args      The program doesn't take any commandline parameters.
     */
    public static void main(String[] args){
        ExternalInventorySystem inventory = new ExternalInventorySystem();
        ExternalAccountingSystem accounting = new ExternalAccountingSystem();
        DiscountDatabase discounts = new DiscountDatabase();
        Printer printer = new Printer();
        
        Controller controller = new Controller(accounting, inventory, discounts, printer);
        
        try{
            View view = new View(controller);
            view.runHardcodedSaleProcess();
        }
        catch(IOException exc){
            System.out.println("Unable to start program.");
            exc.printStackTrace();
        }
        
    }
    
}
