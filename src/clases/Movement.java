/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.sql.Timestamp;
import utilities.Util;
/**
 *
 * @author Saray
 */
public class Movement implements Serializable{
    
    /**
     * Attributes
     */
    private long movementId;
    private double amount;
    private double balance;
    private String description;
    private java.sql.Timestamp timestamp;
    private long accountId;
    
    /**
     * Constructor whitout params
     */
    public Movement(){
        
    } 
    public void setDatos(long accountIid){
        movementId = (long) Util.leerFloat("Introduce el id del movimiento: ");
        System.out.println("Introduce la cantidad: ");
        amount = Util.leerDouble();
         System.out.println("Introduce el balance: ");
        balance = Util.leerDouble();
        description = Util.introducirCadena("Introduce una descripcion: ");
        timestamp = new Timestamp(System.currentTimeMillis());
        accountId = accountIid;
        
    }
    
    /**
     * @return the movementId
     */
    public long getMovementId() {
        return movementId;
    }

    /**
     * @param movementId the movementId to set
     */
    public void setMovementId(long movementId) {
        this.movementId = movementId;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the timestamp
     */
    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(java.sql.Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    
    
}
