/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.controller;

/**
 *Thrown when a system operation cant be fulfilled by the controller.
 *
 */
public class SystemOperationFailureException extends Exception{
    
    /**
     * Creates a new instance representing the error described in the specified
     * message.
     * @param msg       A message that describes the error
     * @param cause     The exception that caused the system operation to fail.
     */
    public SystemOperationFailureException(String msg, Exception cause){
        super(msg, cause);
    }
    
}
