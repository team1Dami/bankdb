/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
/**
 * @author Saray
 */
public class Account implements Serializable{  
    /**
     * Attributes
     */
    private long accountId;
    private float balance;
    private float beginBalance;
    private float beginBalanceTimestamp;
    private double creditLine;
    private String description;
    private int type;   // standard or credit 
    
    
    public void getAccountData(){
        System.out.println("Balance: "+balance);
        System.out.println("Begin Balance: "+beginBalance);
        System.out.println("Begin Balance timestamp: "+beginBalanceTimestamp);
        System.out.println("Credit line: "+creditLine);
        System.out.println("Description: "+description);
        System.out.println("Type: "+type);
        
    }
    /**
     * Constructor without args
     */
    public Account(){
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

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    /**
     * @return the beginBalance
     */
    public float getBeginBalance() {
        return beginBalance;
    }

    /**
     * @param beginBalance the beginBalance to set
     */
    public void setBeginBalance(float beginBalance) {
        this.beginBalance = beginBalance;
    }

    /**
     * @return the beginBalanceTimestamp
     */
    public float getBeginBalanceTimestamp() {
        return beginBalanceTimestamp;
    }

    /**
     * @param beginBalanceTimestamp the beginBalanceTimestamp to set
     */
    public void setBeginBalanceTimestamp(float beginBalanceTimestamp) {
        this.beginBalanceTimestamp = beginBalanceTimestamp;
    }

    /**
     * @return the creditLine
     */
    public double getCreditLine() {
        return creditLine;
    }

    /**
     * @param creditLine the creditLine to set
     */
    public void setCreditLine(double creditLine) {
        this.creditLine = creditLine;
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
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
    
}
