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
    
    public void setCustomer(Customer cust) throws Exception{
        DAO dbDao = new DAO();
        boolean esta=dbDao.getCustomerId(cust.getCustomerId());
        if(!esta)
        dbDao.createCustomer(cust);
    }
    
    public void setAccountCustomer(long cusId, long accId){
        DAO dbDao = new DAO();
        boolean customerId=dbDao.getCustomerId(cusId);
        boolean accountId=dbDao.getAccountId(accId);
        if(accountId&&customerId){
            dbDao.setCustomerAccount(cusId,accId);
        }
        else{
            System.out.println("No se a creado una relacion");
        }
    }
    
}
