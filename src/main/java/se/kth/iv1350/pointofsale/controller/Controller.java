/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.controller;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pointofsale.integration.DataBaseFailureException;
import se.kth.iv1350.pointofsale.integration.DiscountDatabase;
import se.kth.iv1350.pointofsale.integration.ExternalAccountingSystem;
import se.kth.iv1350.pointofsale.integration.ExternalInventorySystem;
import se.kth.iv1350.pointofsale.integration.ItemDTO;
import se.kth.iv1350.pointofsale.integration.ItemNotFoundException;
import se.kth.iv1350.pointofsale.integration.Printer;
import se.kth.iv1350.pointofsale.model.Register;
import se.kth.iv1350.pointofsale.model.Sale;
import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.model.Receipt;
import se.kth.iv1350.pointofsale.model.RevenueObserver;


/**
 * This is the programs only controller. All calls to the model and integration
 * pass through this controller.
 * 
 */
public class Controller {
    private ExternalAccountingSystem accountingSystem;
    private ExternalInventorySystem inventorySystem;
    private DiscountDatabase discountDatabase;
    private Printer printer;
    private Register register;
    private Sale sale;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    
    /**
     * Creates an instance of the controller.
     * @param accounting        The External Accounting System
     * @param inventory         The External Inventory System
     * @param discounts         The External Discount Database
     */
    public Controller(ExternalAccountingSystem accounting, ExternalInventorySystem inventory, DiscountDatabase discounts, Printer printer){
        this.accountingSystem = accounting;
        this.discountDatabase = discounts;
        this.inventorySystem = inventory;
        this.register = new Register(0);
        this.printer = printer;
    }
    
    /**
     * Starts a new sale.
     * This method must be called before doing anything in a new sale.
     */
    public void startSale(){
        sale = new Sale();
        sale.addRevenueObservers(revenueObservers);
    }
    
    /**
     * A scanned item gets recorded in the sale and the 
     * current state of the sale process is returned
     * @param itemID              The scanned items itemID
     * @return                    A DTO describing the current state of the sale
     * @throws ItemNotFoundException        Thrown if the itemID is not found in
     *                                      the inventory
     * @throws SystemOperationFailureException Thrown if it can't perform the operation
     *                                         for any other reason than the ID not existing.
     */
    public SaleDTO scanItem(int itemID)throws ItemNotFoundException, SystemOperationFailureException{
        try {
            ItemDTO itemDTO = inventorySystem.getItemDescription(itemID);
            sale.addItem(itemDTO);
            return sale.getSaleDTO();
        } catch (DataBaseFailureException exc) {
            throw new SystemOperationFailureException("Could not reach inventory database", exc);
        }
    }
    /**
     * After scanning an item lets user add an amount of the same item
     * @param quantity      amount of the same item
     * @return saleDTO      A DTO representing the current sale
     */
    public SaleDTO enterQuantity(int quantity){
        sale.adjustQuantityOfLastItem(quantity);
        SaleDTO currentSaleDTO = sale.getSaleDTO();
        return currentSaleDTO;
    }
    /**
     * Ends the current sale and presents total price and VAT.
     */
    public SaleDTO endSale(){
        SaleDTO saleDTO = sale.getSaleDTO();
        return saleDTO;
    }
    /**
     * Stores sale information in accounting system, updates inventory and generates a receipt.
     * @param amount        The amount paid by customer
     * @return receipt      A receipt containing information about the sale
     */
    public Receipt pay(double amount){
        Receipt receipt = sale.pay(amount);
        accountingSystem.updateAccounting(receipt);
        SaleDTO saleDTO = sale.getSaleDTO();
        inventorySystem.updateInventory(saleDTO);
        register.updateRegisterBalance(amount);
        printer.printReceipt(receipt);
        return receipt;
    }
    
    
    /**
     * Reguests a discount for the sale and applies any available discounts.
     * @param customerID        The customers identification number
     * @return                  A SaleDTO describing the sale after discounts 
     *                          have been applied.
     */
    public SaleDTO requestDiscount(int customerID){
        SaleDTO saleDTOBeforeDiscount = sale.getSaleDTO();
        
        double discountAmount = discountDatabase.getDiscount(customerID, saleDTOBeforeDiscount);
        sale.applyDiscount(discountAmount);
        SaleDTO saleDTOAfterDiscount = sale.getSaleDTO();
        
        return saleDTOAfterDiscount;   
    }
    
    /**
     * Adds an observer to be notified about the total revenue since the start of the program.
     * @param observer      A class implementing the RevenueObserver interface
     */
    public void addRevenueObserver(RevenueObserver observer){
        revenueObservers.add(observer);
    }
}

