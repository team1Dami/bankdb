/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
   public  Customer getCustomerData(long customerId){
       Customer ret = new Customer();
       try{
            this.openConnection();
            
            statement = connection.createStatement();
            
            String select = "select * from customer where id = "+customerId+"";
            ResultSet resultSet = statement.executeQuery(select);
		
		while(resultSet.next()) {
                    ret.setCustomerId(customerId);
                    ret.setFirstName(resultSet.getString("firstName"));
                    ret.setLastName(resultSet.getString("lastName"));
                    ret.setEmail(resultSet.getString("email"));
                        ret.setCity(resultSet.getString("city"));
                        ret.setMiddleInitial(resultSet.getString("middleInitial"));
                        ret.setPhone(resultSet.getLong("phone"));
                        ret.setState(resultSet.getString("state"));
                        ret.setStreet(resultSet.getString("street"));
                        ret.setZip(resultSet.getInt("zip"));
		}
		resultSet.close();
            
            this.closeConnection();   
       }
       catch(SQLException sqlE){
            System.out.println("Customer can not found or doesn't exist");
       }
       catch(Exception e){
            System.out.println("No se ha podido establecer conexión con la base de datos");
       }    
        
      
       return ret;
    }
   
   private void openConnection() throws Exception {
	
        //Class.forName("com.mysql.cj.jdbc.Driver");
        String path = "jdbc:mysql://localhost:3306/bankdb";
	connection = DriverManager.getConnection(path, "root", "abcd*1234");
      
    }
   
   private void closeConnection() throws SQLException {
	statement.close();
	connection.close();
        
        System.out.println("Connection has been closed");
    }
}
