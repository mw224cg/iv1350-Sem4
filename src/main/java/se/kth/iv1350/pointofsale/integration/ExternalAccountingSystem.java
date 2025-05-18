/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pointofsale.model.Receipt;

/**
 * Stores Receipts from ended sales.
 * 
 */
public class ExternalAccountingSystem {
    private List<Receipt> receipts;
    private static final ExternalAccountingSystem EXTERNAL_ACCOUNTING_SYSTEM = new ExternalAccountingSystem();
    
    /**
     * Creates a new instance of the accounting system.
     */
    private ExternalAccountingSystem(){
        this.receipts = new ArrayList<>();
    }
    
    /**
     * @return      The only instance of this singleton.
     */
    public static ExternalAccountingSystem getExternalAccountingSystem(){
        return EXTERNAL_ACCOUNTING_SYSTEM;
    }
    
    /**
     * Records a receipt in the accounting system
     * @param receipt       The receipt to be saved.
     */
    public void updateAccounting(Receipt receipt){
        receipts.add(receipt);
    }
    
}
