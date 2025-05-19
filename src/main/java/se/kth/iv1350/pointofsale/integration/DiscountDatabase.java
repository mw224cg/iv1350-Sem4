/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import se.kth.iv1350.pointofsale.integration.discountstrategies.CustomerBasedDiscount;
import se.kth.iv1350.pointofsale.integration.discountstrategies.DiscountStrategy;
import se.kth.iv1350.pointofsale.integration.discountstrategies.ItemBasedDiscount;
import se.kth.iv1350.pointofsale.integration.discountstrategies.TotalCostBasedDiscount;

import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.model.SoldItem;

/**
 *  Represents a discount database. Responsible for calculating discounts.
 * 
 */
public class DiscountDatabase {
    private static final DiscountDatabase DISCOUNT_DATABASE = new DiscountDatabase();
    
    private Map<Integer, DiscountDTO> discountMap;
    private Set<Integer> EligbleCustomerIDs;
    private List<DiscountStrategy> discountStrategies;
    
    /**
     * Creates a new instance of the database.
     */
    private DiscountDatabase(){
        this.discountMap = new HashMap<>();
        this.EligbleCustomerIDs = new HashSet<>();
        this.discountStrategies = new ArrayList<>();
        
        addDiscounts();
        addEligbleCustomerIDs();
        addDiscountStrategies();
    }
    
    /**
     * @return The only instance of this singleton.
     */
    public static DiscountDatabase getDiscountDatabase(){
        return DISCOUNT_DATABASE;
    }
    
    private void addDiscounts(){
        discountMap.put(1234, new DiscountDTO(1234, 0.10, true));
        discountMap.put(2222, new DiscountDTO(2222, 0.15, false));
        discountMap.put(5555,  new DiscountDTO(5555, 0.25, true));
    }
    
    private void addEligbleCustomerIDs(){
        EligbleCustomerIDs.add(123456);
        EligbleCustomerIDs.add(549083);
        EligbleCustomerIDs.add(924714);
        EligbleCustomerIDs.add(124533);
        EligbleCustomerIDs.add(214554);
    }
    
    private void addDiscountStrategies(){
        discountStrategies.add(new ItemBasedDiscount(discountMap,EligbleCustomerIDs));
        discountStrategies.add(new TotalCostBasedDiscount(500,0.15));
        discountStrategies.add(new CustomerBasedDiscount(EligbleCustomerIDs,0.05));
    }
    
    public double getDiscount(int customerID, SaleDTO saleDTO){
        double totalDiscount = 0;
        
        for(DiscountStrategy discount : discountStrategies){
            totalDiscount += discount.calculateDiscount(saleDTO, customerID);
        }
        return totalDiscount;
    }
    
    
}
