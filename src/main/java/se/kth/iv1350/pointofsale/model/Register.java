/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;

import se.kth.iv1350.pointofsale.integration.ItemDTO;
import java.util.List;

/**
 * The register at the POS
 * 
 */
public class Register {
    private double totalCashBalance;
    
    /**
     * Creates a new instance of the register.
     * @param amount    The amount of cash in the register.
     */
    public Register(double amount){
        this.totalCashBalance = amount;
    }
    
    /**
     * Adds an amount to the register.
     * @param amount    The amount to be added.
     */
    public void updateRegisterBalance(double amount){
    this.totalCashBalance +=amount;
    }
    
    public double getBalance(){
        return totalCashBalance;
    }
}
