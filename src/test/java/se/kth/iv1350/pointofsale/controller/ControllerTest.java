/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.pointofsale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointofsale.integration.DataBaseFailureException;
import se.kth.iv1350.pointofsale.integration.DiscountDatabase;
import se.kth.iv1350.pointofsale.integration.ExternalAccountingSystem;
import se.kth.iv1350.pointofsale.integration.ExternalInventorySystem;
import se.kth.iv1350.pointofsale.integration.ItemNotFoundException;
import se.kth.iv1350.pointofsale.integration.Printer;
import se.kth.iv1350.pointofsale.model.Receipt;
import se.kth.iv1350.pointofsale.model.Sale;
import se.kth.iv1350.pointofsale.model.SaleDTO;

/**
 *
 * @author Marcus
 */
public class ControllerTest {
    private Controller controller;
    
    public ControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        ExternalInventorySystem inventorySystem = ExternalInventorySystem.getExternalInventorySystem();
        ExternalAccountingSystem accountingSystem = ExternalAccountingSystem.getExternalAccountingSystem();
        DiscountDatabase discountDatabase = DiscountDatabase.getDiscountDatabase();
        Printer printer = new Printer();
        controller = new Controller(accountingSystem, inventorySystem, discountDatabase, printer);
        controller.startSale();
    }
    
    @AfterEach
    public void tearDown() {
    }

   @Test
   public void testInvalidItemIDThrowsItemNotFoundException(){
       int invalidID = 9999;
       
       try {
           controller.scanItem(invalidID);
           fail("Invalid item ID does not generate ItemNotFoundException");
       } catch (ItemNotFoundException exc) {
           assertTrue((exc.getMissingItemID() == invalidID), "Exception message should contain the missing Item ID."
                    + " Actual message: " +exc.getMessage());
       } catch(Exception exc){
           fail("Wrong type of exception thrown: " + exc);
       }
   }
   
   @Test
   public void testDbFailExceptionThrowsSystemOperationFailureException(){
       int dbFailID = 8008;
       try {
           controller.scanItem(dbFailID);
           fail("The itemID: " +dbFailID + " should generate SystemOperationFailureException");
       } catch (SystemOperationFailureException exc) {
           assertTrue(exc.getCause() instanceof DataBaseFailureException, "Cause of exception should be DataBaseFailureException");
       } catch (Exception exc){
           fail("Wrong type of exception thrown: " + exc);
       }
   }
    
}
