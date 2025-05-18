/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.view;

import se.kth.iv1350.pointofsale.model.RevenueObserver;

/**
 *Displays the sum of the costs for all the sales done since the program started.
 */
public class TotalRevenueView implements RevenueObserver{
    private double totalRevenue = 0;
    private static final TotalRevenueView TOTAL_REVENUE_VIEW = new TotalRevenueView();
    
    private TotalRevenueView(){
        
    }
    
    public static TotalRevenueView getTotalRevenueView(){
        return TOTAL_REVENUE_VIEW;
    }
    
    
    
    @Override
    public void newRevenue(double revenue){
        totalRevenue += revenue;
        System.out.println("-----------TOTAL-REVENUE-----------------");
        System.out.printf("Total revenue since program start: %.2f SEK %n", revenue);
        System.out.println("-----------------------------------------\n");
    }
}
