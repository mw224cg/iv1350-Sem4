/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.util;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class responsible for formatting date and time in a standard way.
 */
public class SweDateTimeFormatter {
    private static final Locale locale = new Locale("sv", "SE");
    
    private SweDateTimeFormatter(){
        
    }
    
    /**
     * @return A string with the current date and time
     */
    public static String getCurrentDateTime() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).localizedBy(locale);
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
    
    public static String formatDateTime(LocalDateTime time){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).localizedBy(locale);
        return time.format(formatter);
    }
    
}
