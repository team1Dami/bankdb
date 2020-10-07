/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import clases.Account;
import clases.Customer;

import clases.Movement;
import javax.xml.transform.Source;

import clases.CustomerAccount;
import control.Application;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public static void main(String[] args) throws Exception {
        
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
    private static void createCustomer()throws Exception {
       Application app=new Application();
       Customer cust =new Customer();
       cust.setDatos();
       app.setCustomer(cust);
    }
    /**
     * 
     */
    private static void consultCustomerData() {
       
        Application app = new Application();
       
        try {
            Long customerId = Util.leerLong("Introduce the id of the customer: ");
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
    private static void consultAccounsOfAClient() throws Exception{
        Application app = new Application();
        String c_id;
        long id;
        System.out.println("Introduce tu ID: ");
        c_id=Util.introducirCadena();
        id=Long.parseLong(c_id);
        ArrayList <Account> cuentas = new ArrayList<>();
        cuentas = app.getCustomerAccount(id);
        for(int cont = 0; cont<cuentas.size(); cont++){
            cuentas.get(cont).getAccountData();
        }
    }

    /**
     * 
     */

    private static void consultAccounsOfAClient() throws Exception {
        Application app = new Application();
        String c_id;
        long id;
        System.out.println("Introduce tu ID: ");
        c_id=Util.introducirCadena();
        id=Long.parseLong(c_id);
        ArrayList <Account> cuentas = new ArrayList<>();
        cuentas = app.getCustomerAccount(id);
        for(int cont = 0; cont<cuentas.size(); cont++){
            System.out.println("\n\n"+(cont+1)+". cuenta de el cliente");
            cuentas.get(cont).getAccountData();
        }
    }
    /**
     * 
     */
    private static void consultMovementsOfAnAccount() throws Exception {
        ArrayList<Movement> movimientos = new ArrayList<Movement>();
        Application app = new Application();
        long acc_id;
        String id;
        //System.out.println("Introduce la ID de la cuenta a buscar: ");
        acc_id=Util.leerLong("Introduce la ID de la cuenta a buscar: ");
        movimientos=app.getAccountMovement(acc_id);
        for(int cont=0; cont< movimientos.size(); cont++){
            System.out.println("\n\n"+(cont+1)+". Movimiento de la cuenta");
            System.out.println("ID: "+movimientos.get(cont).getMovementId());
            System.out.println("Ammount: "+movimientos.get(cont).getAmount());
            System.out.println("Balance: "+movimientos.get(cont).getBalance());
            System.out.println("Description: "+movimientos.get(cont).getDescription());
            System.out.println("Timestamp: "+movimientos.get(cont).getTimestamp());
            System.out.println("Account ID: "+movimientos.get(cont).getAccountId());
        }
        
    }

    private static void createCustomerAccount() {
         Application app = new Application();
       
        try {
            Long customerId = Util.leerLong("Introduce the id of the customer: ");
             if(app.getCustomerId(customerId)){  // comprobamos si el cliente existe:
                
                Account newAccount = new Account();
       
                do {
                    newAccount.setAccountId(createAccountId());
                    if(!app.accountExist(newAccount.getAccountId())) {
                
                    newAccount.setBalance(Util.leerFloat("Introduce de balance of the account"));
                    newAccount.setBeginBalance(newAccount.getBalance());
                    java.sql.Timestamp today = java.sql.Timestamp.valueOf(LocalDateTime.now());
                    newAccount.setBeginBalanceTimestamp(today);
                    newAccount.setCreditLine(Util.leerDouble("Introduce the credit line: "));
                    newAccount.setDescription(Util.introducirCadena("Introduce the description: "));
                    newAccount.setType(Util.leerInt("Select 0 (whithout credit) or 1 (whit credit): ", 0, 1));

                    app.setAccount(customerId, newAccount);

                    CustomerAccount customerAccount = new CustomerAccount();

                    customerAccount.setCustomerId(customerId);
                    customerAccount.setAccountId(newAccount.getAccountId());

                    app.setCustomerAccount(customerAccount);

                     System.out.println("The new account has been created");
                    }
                } while (app.accountExist(newAccount.getAccountId()));
                
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
        
        String strAccount = accountId.toString();
        strAccount = strAccount.substring(0, 10);
        
        try {
        accountId = Long.parseLong(strAccount);
        } catch (Exception e){
            System.out.println("The account id can not be created");
        }
        return accountId;
    }
      
  /**
     * 
     */
    private static void addCustomerToAccount() {
        Application app=new Application();
        long cusId=0;
        long accId=0;
        cusId=Util.leerLong("Customer Id");
        accId=Util.leerLong("Account Id");
        app.setAccountCustomer(cusId, accId);
    }

    /**
     * 
     */
    private static void consultDetailsOfAnAccounts() {
        
        Application app = new Application();
        try {
            Long accountId = Util.leerLong("Introduce the id of the account: ");
            System.out.println("Id: "+accountId);
            Account account = app.getAccountData(accountId);
            if(account != null){
               account.getAccountData();
            }
        } catch (Exception e){
            System.out.println("An error has ocurred");
        }
    }

     /**
     * 
     */
    private static void makeMovementOnAnAccount() {
        Application app = new Application();
        try {
            Long accountId = Util.leerLong("Introduce the id of the account: ");
            System.out.println("Id: "+accountId);
            app.createMovement(accountId);
 
        } catch (Exception e){
            System.out.println("An error has ocurred");
        }
    }

    private static void consultMovementsOfAnAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
