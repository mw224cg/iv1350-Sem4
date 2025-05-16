/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;

/**
 * A listener interface for recieving information about performed sales.
 * The class to recieve such notifications implements this interface
 */
public interface RevenueObserver {
    
    void newRevenue(double revenue);
}
