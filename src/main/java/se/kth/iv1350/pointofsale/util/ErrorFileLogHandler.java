/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import  java.io.FilterWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Logs exceptions to textfile. 
 */
public class ErrorFileLogHandler {
    private static final String LOG_FILE = "pos_exc_log.txt";
    private PrintWriter logFile;
    
    /**
     * Creates new instance of the error logger and opens a log file for appending
     * @throws IOException      If the file can't be opnend
     */
    public ErrorFileLogHandler() throws IOException{
        logFile = new PrintWriter(new FileWriter(LOG_FILE, true),true);
    }
    
    public void log(Object exc){
        Exception exception = (Exception) exc;
        
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(exception.getMessage());
        logFile.println(logMsgBuilder);
        exception.printStackTrace(logFile);
    }
    
    private String createTime() {
        Locale locale = new Locale("sv", "SE");
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).localizedBy(locale);
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
    
}
