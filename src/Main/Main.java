/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
        System.out.println("1- Create	customer");
        System.out.println("2- Consult customer data");
        System.out.println("3- Consult accounts of a client");
        System.out.println("4- Create	customer account");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     */
    private static void addCustomerToAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
