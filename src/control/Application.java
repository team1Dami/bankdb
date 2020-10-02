/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Customer;
import clases.CustomerAccount;

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

    public boolean getCustomerId(Long customerId) throws Exception{
        DAO dbDao = new DAO();
        return dbDao.getCustomerId(customerId);
    }
    
    
}
