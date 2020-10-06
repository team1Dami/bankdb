/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Account;
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
     * @throws java.lang.Exception
     */
    public Customer getCustomerData(long customerId) throws Exception{
        DAO dbDao = new DAO();
        Customer ret = dbDao.getCustomerData(customerId); 
        return ret; 
    }
    /**
     * 
     * @param customerId
     * @return
     * @throws Exception 
     */
    public boolean getCustomerId(Long customerId) throws Exception{
        DAO dbDao = new DAO();
        return dbDao.getCustomerId(customerId);
    }
    /**
     * 
     * @param customerId
     * @param account
     * @throws Exception 
     */
    public void setAccount(Long customerId, Account account) throws Exception {
        DAO dbDao = new DAO();
        
        dbDao.setAccount(customerId, account);
    }

    public void setCustomerAccount(CustomerAccount customerAccount) throws Exception{
        DAO dbDao = new DAO();
        
        dbDao.setCustomerAccount(customerAccount);
    }

    public boolean accountExist(long accountId) throws Exception {
         DAO dbDao = new DAO();
         
         boolean blnExistAccount = dbDao.accountExist(accountId);
        return blnExistAccount;
    }
    
}
