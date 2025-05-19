/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.util;

import se.kth.iv1350.pointofsale.model.RevenueObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class appends a file with information about the total revenue since program start whenever a sale is made.
 * 
 */
public class TotalRevenueFileOutput implements RevenueObserver{
    private static final String REVENUE_FILE = "total_revenue.txt";
    private double totalRevenue =0;
    private PrintWriter writer;
    
    public TotalRevenueFileOutput(){
        try {
            this.writer = new PrintWriter(new FileWriter(REVENUE_FILE,true));
            writer.println();
            writer.println("-------------New program start------------");
        } catch (IOException exc) {
            System.out.println("Could not open revenue file: " + exc.getMessage());
        }
    }
    
    @Override
    public void newRevenue(double revenue){
        totalRevenue += revenue;
        writer.println(buildString(totalRevenue));
        writer.flush();
    }
    
    private String buildString(double totalRevenue){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SweDateTimeFormatter.getCurrentDateTime());
        stringBuilder.append("--New total revenue registered: ");
        stringBuilder.append(String.format("%.2f", totalRevenue).replace(".", ":"));
        stringBuilder.append(" SEK");
        return stringBuilder.toString();
    }
}
