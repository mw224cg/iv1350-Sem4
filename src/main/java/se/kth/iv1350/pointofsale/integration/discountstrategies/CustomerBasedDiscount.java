/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration.discountstrategies;

import java.util.Set;
import se.kth.iv1350.pointofsale.model.SaleDTO;

/**
 *
 */
public class CustomerBasedDiscount implements DiscountStrategy{
    private final Set<Integer> eligbleCustomerIDs;
    private final double discountPercentage;
    
    /**
     * Creates a new instance of a customer-based discount.
     * @param eligbleCustomerIDs        A set of customer IDs eligble for the discount.
     * @param discountPercentage        The percentage to be deducted from the total cost of the sale.
     */
    public CustomerBasedDiscount(Set<Integer> eligbleCustomerIDs, double discountPercentage){
        this.eligbleCustomerIDs = eligbleCustomerIDs;
        this.discountPercentage = discountPercentage;
    }

    /**
     * Calculates discount based on customer identification.
     * @param saleDTO           Contains information about the sale.
     * @param customerID        The customers identificatio
     * @return                  An amount to be deducted of the total cost of the sale. 
     */
    @Override
    public double calculateDiscount(SaleDTO saleDTO, int customerID){
        double discount = 0;
        if(eligbleCustomerIDs.contains(customerID)){
            discount = saleDTO.getTotalPrice() * discountPercentage;
            return discount;
        }
        return discount;
    }
    
    
    
}
