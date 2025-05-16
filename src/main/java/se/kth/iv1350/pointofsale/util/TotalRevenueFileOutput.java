/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.util;

import se.kth.iv1350.pointofsale.model.RevenueObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Marcus
 */
public class TotalRevenueFileOutput implements RevenueObserver{
    private static final String REVENUE_FILE = "total_revenue.txt";
    private double totalRevenue =0;
    
    @Override
    public void newRevenue(double revenue){
        totalRevenue += revenue;
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(REVENUE_FILE, false))) {
            writer.println("Total revenue: " + totalRevenue + " SEK");
        } catch (IOException e) {
            System.out.println("Could not write revenue to file: " + e.getMessage());
        }
        
    }
    
}
