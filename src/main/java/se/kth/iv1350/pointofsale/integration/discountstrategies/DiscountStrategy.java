/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration.discountstrategies;

import java.util.List;
import se.kth.iv1350.pointofsale.model.SaleDTO;

/**
 * Startegy interface for calculating discounts based on different discount rules.
 */
public interface DiscountStrategy {
    
    /**
     * Calculates the discount based on sale information and customer identification
     * @param saleDTO       Contains information about the sale
     * @param customerID    The customers identification
     * @return              An amount to be discounted, if no discount applicable
     *                      0 is returned.
     */
    public double calculateDiscount(SaleDTO saleDTO, int customerID);
    
}
