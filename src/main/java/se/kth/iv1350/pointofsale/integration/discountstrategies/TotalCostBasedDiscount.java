/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration.discountstrategies;

import se.kth.iv1350.pointofsale.model.SaleDTO;

/**
 * A discount that applies a percentage discount if the total cost exceeds a threshold.
 */
public class TotalCostBasedDiscount implements DiscountStrategy{
    private final double threshold;
    private final double discountPercentage;
    
    
    /**
     * Creates a new instance of a total cost based discount 
     * @param threshold                 The total cost of the sale that needs to
     *                                  be exceeded for the discount to activate
     * @param discountPercentage        The discount percentage to deducted  
     */
    public TotalCostBasedDiscount(double threshold, double discountPercentage){
        this.threshold = threshold;
        this.discountPercentage = discountPercentage;
    }
    
    /**
     * Calculates the discount based on total cost.
     * @param saleDTO           Contains information about the sale.
     * @param customerID        The customers identificatio
     * @return                  An amount to be deducted of the total cost of the sale.
     */
    @Override
    public double calculateDiscount(SaleDTO saleDTO, int customerID) {
        double discount = 0;
        double totalCostOfSale = saleDTO.getTotalPrice();
        if (totalCostOfSale >= threshold) {
            discount = totalCostOfSale * discountPercentage;
            return discount;
        }
        return discount;
    }
    
    
    
}
