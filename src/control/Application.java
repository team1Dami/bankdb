
package control;

import clases.Account;
import clases.Customer;
import clases.Movement;
import clases.CustomerAccount;
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
     /**
     * Method to obtain Customer data
     * @param customerId to make the query
     * @return customer
     */
    public ArrayList<Account> getCustomerAccount(long customerId) throws Exception{

        DAO dbDao = new DAO();
        ArrayList<Account> ret = dbDao.getCustomerAccount(customerId);
        return ret; 
    }

    
     public ArrayList<Movement> getAccountMovement(long account_id) throws Exception{
        DAO dbDao = new DAO();
        ArrayList<Movement> ret = dbDao.getAccountMovemnt(account_id);
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
   
 public Account getAccountData(long AccountId) throws Exception{
        DAO dbDao = new DAO();
        Account ret = dbDao.getAccountData(AccountId);
        return ret; 
    }
     public void createMovement(long accountId) throws Exception{
         System.out.println("Estoy en el aplication");
         DAO dbDao = new DAO();
        dbDao.createMovement(accountId);
     }
}

