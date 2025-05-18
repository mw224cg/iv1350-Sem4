/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration.discountstrategies;

import java.util.List;
import java.util.Map;
import java.util.Set;
import se.kth.iv1350.pointofsale.integration.DiscountDTO;
import se.kth.iv1350.pointofsale.integration.ItemDTO;
import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.model.SoldItem;

/**
 * A discount that applies discounts based on induvidual items.
 * Items can have different discounts and some discounts are only available 
 * for registred customers. 
 */
public class ItemBasedDiscount implements DiscountStrategy{
    private final Map<Integer, DiscountDTO> discounts;
    private final Set<Integer> eligbleCustomerIDs;
    
    
    /**
     * Creates a new instance of item based discount rules
     * @param discounts             A map of item IDs and their corresponding discount
     * @param eligbleCustomerIDs    A set of customerIDs eligble for customer-only
     *                              discounts.
     */
    public ItemBasedDiscount(Map<Integer, DiscountDTO> discounts, Set<Integer> eligbleCustomerIDs){
        this.discounts = discounts;
        this.eligbleCustomerIDs = eligbleCustomerIDs;
    }
    
    /**
     * Calculates the total discount of a sale based on item-specific discount rules
     * @param saleDTO       Contains information about the sale.
     * @param customerID    The customers identification
     * @return              An amount to be deducted of the total cost of the sale.
     */
    @Override
    public double calculateDiscount(SaleDTO saleDTO, int customerID){
        boolean isMember = eligbleCustomerIDs.contains(customerID);
        double discount = 0;
        
        List<SoldItem> itemsInSale = saleDTO.getSoldItems();
        for(SoldItem item : itemsInSale){
            DiscountDTO discountDTO = discounts.get(item.getItemDTO().getItemID());
            if(discountDTO != null && (isMember || !discountDTO.isOnlyForMembers())){
                discount += calculateItemDiscount(item, discountDTO);
            }
        }
        return discount;
    }
    
    private double calculateItemDiscount(SoldItem item, DiscountDTO discount){
        return item.getItemDTO().getPrice() * item.getQuantitySold() * discount.getDiscountPercentage();
    }
}
