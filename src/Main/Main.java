/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import clases.Account;
import clases.Customer;
import clases.CustomerAccount;
import control.Application;
import java.time.LocalDateTime;
import java.util.Random;

import utilities.Util;

/**
 *
 * @author Saray
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int option = 0;
        
        do {
            option = menu();
            
            switch (option){
                case 1:
                    createCustomer();
                    break;
                case 2:
                    consultCustomerData();
                    break;
                case 3:
                    consultAccounsOfAClient();
                    break;
                case 4:
                    createCustomerAccount();
                    break;
                case 5:
                    addCustomerToAccount();
                    break;
                case 6:
                    consultDetailsOfAnAccounts();
                    break;
                case 7:
                    makeMovementOnAnAccount();
                    break;
                case 8:
                    consultMovementsOfAnAccount();
                    break;
                case 9:
                    break;
            }
            
        }while (option != 9);
    }
    
    /**
     * 
     * @return option 
     */
    private static int menu() {
        
        int ret = 0;
        
        System.out.println("- - - - - - - M E N U - - - - - - -");
        System.out.println("1- Create customer");
        System.out.println("2- Consult customer data");
        System.out.println("3- Consult accounts of a client");
        System.out.println("4- Create customer account");
        System.out.println("5- Add customer to account");
        System.out.println("6- Consult details of an account");
        System.out.println("7- Make movement on an account");
        System.out.println("8- Consult movements of an account");
        System.out.println("9- Exit");
        
        ret = Util.leerInt("Choose an option: ");
        
        return ret;      
    }

    /**
     * 
     */
    private static void createCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * 
     */
    private static void consultCustomerData() {
       
        Application app = new Application();
       
        try {
            Long customerId = Util.leerLong("Introduce the id of the customer: ");
            System.out.println("Id: "+customerId);
            Customer customer = new Customer();
            customer = app.getCustomerData(customerId);
            if(customer.getCustomerId() != 0){
               customer.getCustomerData();
            }
        } catch (Exception e){
            System.out.println("An error has ocurred");
        }
    }
    /**
        * 
        */
    private static void consultAccounsOfAClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     */
    private static void createCustomerAccount() {
         Application app = new Application();
       
        try {
            Long customerId = Util.leerLong("Introduce the id of the customer: ");
             if(app.getCustomerId(customerId)){  // comprobamos si el cliente existe:
                
                Account newAccount = new Account();
       
                newAccount.setAccountId(createAccountId());
                System.out.println(newAccount.getAccountId());
                newAccount.setBalance(Util.leerFloat("Introduce de balance of the account"));
                newAccount.setBeginBalance(newAccount.getBalance());
                java.sql.Timestamp today = java.sql.Timestamp.valueOf(LocalDateTime.now());
                newAccount.setBeginBalanceTimestamp(today);
                newAccount.setCreditLine(Util.leerDouble("Introduce the credit line: "));
                newAccount.setDescription(Util.introducirCadena("Introduce the description: "));
                newAccount.setType(Util.leerInt("Select 0 (whithout credit) or 1 (whit credit): ", 0, 1));
               
                app.setAccount(customerId, newAccount);
                
                CustomerAccount customerAccount = new CustomerAccount();
            } else {
                 System.out.println("The id of the client doesn't exist");
             }
          
           
        } catch (Exception e){
            System.out.println("An error has ocurred");
        }
    }
    
    /**
     * 
     */
    private static long createAccountId() {
        Long accountId = null;
        
        accountId = new Random().nextLong();
        
        return accountId;
    }
    /**
     * 
     */
    private static void addCustomerToAccount() {
        
        
    }

    /**
     * 
     */
    private static void consultDetailsOfAnAccounts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     */
    private static void makeMovementOnAnAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     */
    private static void consultMovementsOfAnAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
