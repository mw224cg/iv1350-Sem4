/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;

/**
 *Thrown when database can't be reached.
 */
public class DataBaseFailureException extends RuntimeException{
    
    /**
     * Creates a new instance representing failure to reach the database.
     * @param msg       A message describing the error.
     */
    public DataBaseFailureException(String msg){
        super(msg);
    }
    
}
