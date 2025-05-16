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
    
    @Override
    public void newRevenue(double revenue){
        totalRevenue += revenue;
        System.out.println("Total revenue since program start: "+ totalRevenue + " SEK");
    }
}
