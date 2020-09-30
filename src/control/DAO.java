/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Customer;
import clases.CustomerAccount;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Saray
 */
public class DAO {
    
    /**
     * Attributes
     */
  //  static Connection = new Connection("jdbc:mysql://localhost:3306/bankdb", "root", "abcd*1234");
    private Connection conn = null;
 //   private Properties properties;
    private PreparedStatement preparedStmt = null;
    private Statement statement = null;
    
    /**
     * getting the properties file datas
     */
    private ResourceBundle configFile;
    private String driverDB;
    private String urlDB;
    private String userDB;
    private String passwordDB;

    /**
     * 
     * @throws Exception 
     */
    public DAO() throws Exception {
      this.configFile = ResourceBundle.getBundle("control.configFile");
      this.driverDB = this.configFile.getString("Driver");
      this.urlDB = this.configFile.getString("Conn");
      this.userDB = this.configFile.getString("DBUser");
      this.passwordDB = this.configFile.getString("DBPass");
      
      //  System.out.println(urlDB+" "+userDB+" "+passwordDB+" "+driverDB);  
    }
    
    /**
     * 
     * @param customerId 
     * @return Customer to consult the datas
     */
   public  Customer getCustomerData(long customerId){
       Customer ret = new Customer();
       ResultSet resultset = null;
       PreparedStatement preparedStmt = null;
       try{
          
           this.openConnection();         
            String select = "select * from customer where id = "+customerId+"";
            preparedStmt = conn.prepareStatement(select);
         
         ResultSet resultSet = preparedStmt.executeQuery(select);
		
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
                preparedStmt.close();
            
            this.closeConnection();   
       }
       catch(SQLException sqlE){
            System.out.println("Customer can not found or doesn't exist");
       }
       catch(Exception e){
            System.out.println("DB Connection failed");
       }             
       return ret;
    }
   
    boolean setCustomerAccount(Long customerId) {
       CustomerAccount ret = new CustomerAccount();
       ResultSet resultset = null;
       PreparedStatement preparedStmt = null;
       boolean blnCreated = false;
       
       try{
          
           this.openConnection();         
            String insert = "select * from customer where id = "+customerId+"";
            preparedStmt = conn.prepareStatement(insert);
         
         ResultSet resultSet = preparedStmt.executeQuery(insert);
      
            
        }catch (SQLException sqlE) {
            System.out.println("Insert failed");
        }
        return blnCreated;
    }
   private void openConnection()   {
       
       try {
      //  Class.forName(driverDB);
  //      conn = DriverManager.getConnection(urlDB, userDB, passwordDB);
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "abcd*1234");
    
       }
       catch (Exception e){
           System.out.println("No Connec");
       }
    }
   
   private void closeConnection() throws SQLException {  
	conn.close();     
    }

    
  
}