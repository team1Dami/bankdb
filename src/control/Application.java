/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Account;
import clases.Customer;
import clases.Movement;
import java.util.ArrayList;

/**
 * @author Saray
 */
public class Application {
    // es la que actúa como la Manager
    
    /**
     * Method to obtain Customer data
     * @param customerId to make the query
     * @return customer
     */
    public Customer getCustomerData(long customerId) throws Exception{
        DAO dbDao = new DAO();
        Customer ret = dbDao.getCustomerData(customerId);
        return ret; 
    }
    
     /**
     * Method to obtain Customer data
     * @param customerId to make the query
     * @return customer
     */
    public ArrayList<Account> getCustomerAccount(long customerId) throws Exception{
        System.out.println("Estoy en el aplication      "+customerId);
        DAO dbDao = new DAO();
        ArrayList<Account> ret = dbDao.getCustomerAccount(customerId);
        return ret; 
    }
    
     public ArrayList<Movement> getAccountMovement(long account_id) throws Exception{
        DAO dbDao = new DAO();
        ArrayList<Movement> ret = dbDao.getAccountMovemnt(account_id);
        return ret; 
         
     }
}
