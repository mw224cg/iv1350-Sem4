/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.view;

import java.io.IOException;
import java.time.LocalDateTime;
import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.controller.SystemOperationFailureException;
import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.integration.ItemDTO;
import se.kth.iv1350.pointofsale.integration.ItemNotFoundException;
import se.kth.iv1350.pointofsale.model.SoldItem;
import se.kth.iv1350.pointofsale.model.Receipt;
import se.kth.iv1350.pointofsale.util.ErrorFileLogHandler;
/**
 * Placeholder for the real view. Contains hardcoded calls to the system
 * operations in the controller.
 * 
 */
public class View {
    private Controller controller;
    private ErrorMessageHandler errorMessageHandler;
    private ErrorFileLogHandler errorLogger;
    
    private static final int ITEM_ID_NOT_IN_INVENTORY = 99999;
    private static final int COCA_COLA_ITEM_ID = 5555;
    private static final int CONNECTION_ERROR = 8008;
    
    /**
     * Creates a new instance of the view
     * @param controller    The controller that is used for all operations
     * @throws IOException  If unable to open file for logging.
     */
    public View(Controller controller)throws IOException{
        this.controller = controller;
        this.errorMessageHandler = new ErrorMessageHandler();
        this.errorLogger = new ErrorFileLogHandler();
    }
    
    /**
     * A hardcoded method that calls the system operations for the sale process.
     * 
     */
    public void runHardcodedSaleProcess(){
        startSale();
        scanItem(ITEM_ID_NOT_IN_INVENTORY);
        scanItem(CONNECTION_ERROR);
        scanItem(COCA_COLA_ITEM_ID);
        enterQuantity(4);
        endSale();
        requestDiscount(123456);
        pay(500);
    }
    
    private void startSale(){
        controller.startSale();
        System.out.println("New Sale Started...");
    }
    
    private void scanItem(int itemID){
        System.out.println("Scanned Item:");
        try{
            SaleDTO currentSaleInfo = controller.scanItem(itemID);
            displayItemInfo(currentSaleInfo);
            displayTotal(currentSaleInfo);
        }
        catch(ItemNotFoundException exc){
            errorMessageHandler.showErrorMsg("Item ID: " + exc.getMissingItemID() + " not found in inventory");
        }
        catch(SystemOperationFailureException exc){
            errorMessageHandler.showErrorMsg("Lost connection to inventory database. Please try again...");
            errorLogger.log(exc);
        }
        catch(Exception exc){
            errorMessageHandler.showErrorMsg("System operation failed. Please try again...");
            errorLogger.log(exc);
        }
    }
    
    private void enterQuantity(int quantity){
        SaleDTO currentSaleInfo = controller.enterQuantity(quantity);
        String lastItem = currentSaleInfo.getLastSoldItem().getItemDTO().getName();
        System.out.println("Adjusted quantity of item: " + lastItem + " to: " +quantity);
        displayItemInfo(currentSaleInfo);
        displayTotal(currentSaleInfo);
    }
    
    private void endSale(){
        SaleDTO endSaleInfo = controller.endSale();
        
        System.out.println("Total price & VAT for purchase:");
        displayTotal(endSaleInfo);
    }
    
    private void requestDiscount(int customerID){
        SaleDTO saleInfoAfterDiscount = controller.requestDiscount(customerID);
        System.out.println("New Total after discount:");
        displayTotal(saleInfoAfterDiscount);
    }
    
    /**
     * Displays the current sale information.
     * @param currentSaleInfo   Contains information about the current sale
     */
    public void displayItemInfo(SaleDTO currentSaleInfo){
        SoldItem currentSoldItem = currentSaleInfo.getLastSoldItem();
        ItemDTO currentItem = currentSoldItem.getItemDTO();
        
        System.out.println("Item ID: " + currentItem.getItemID());
        System.out.println("Item name: " + currentItem.getName());
        System.out.println("Item cost: " + currentItem.getPrice() + "kr");
        System.out.println("Item VAT: " + currentItem.getVAT() + "%");
        System.out.println("Quantity of item: " + currentSoldItem.getQuantitySold());
    }
    
    private void displayTotal(SaleDTO currentSaleInfo){
        System.out.println();
        System.out.println("Total cost: " + currentSaleInfo.getTotalPrice() + "kr");
        System.out.println("Total VAT: " + currentSaleInfo.getVAT() + "kr\n");
    }
    
    private void pay(double amount){
        Receipt receipt = controller.pay(amount);
        displayReceipt(receipt);
    }
    
    private void displayReceipt(Receipt receipt){
        System.out.println("-----RECEIPT------\n");
        System.out.println("Time of sale: " + receipt.getTime());
        
        for(SoldItem item : receipt.getSoldItems()){
            String name = item.getItemDTO().getName();
            int quantity = item.getQuantitySold();
            double price = item.getItemDTO().getPrice();
            double vat = item.getItemDTO().getVAT();
            double total = price * quantity;
            
            System.out.printf("%s x%d  %.2f kr (VAT %.0f%%)%n", name, quantity, total, vat);
        }
        System.out.println("-------------------");
        System.out.printf("Total VAT: %.2f kr%n", receipt.getTotalVAT());
        System.out.printf("Total cost: %.2f kr%n", receipt.getTotalPrice());
        System.out.printf("Total discount for sale: %.2f kr %n", receipt.getDiscountAmount());
        System.out.println("-------------------");
    }
    
}
