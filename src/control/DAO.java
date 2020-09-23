/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Saray
 */
public class DAO {
    
    /**
     * Attributes
     */
    private Connection connection;
    private Statement statement;
      
    /**
     * 
     * @param customerId 
     * @return Customer to consult the datas
     */
   public  Customer getCustomerData(long customerId) {
       Customer ret = new Customer();
       
       try {
           
       }
       catch(Exception e){
           
       }
        
        return ret;
    }
   
   private void openConnection() throws Exception {
		
        /*
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/bankdb?zeroDateTimeBehavior=convertToNull [root on Default schema]";
	connection = DriverManager.getConnection(url, "root", "abcd*1234");
	statement = connection.createStatement();       
        */
        
       
    }
}
