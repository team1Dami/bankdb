/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Account;
import clases.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Saray
 */
public class DAO {
    
    /**
     * Attributes
     */
    private Connection conn = null;
    private PreparedStatement preparedStmt = null;
    
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
   /**
    * 
    * @param customerId
    * @return true if exist or false if doesn't exist
    */
    public boolean getCustomerId(Long customerId) throws Exception{
   
       boolean blnExist = false;
       
       try{
           this.openConnection();         
            String select = "select id from customer where id = "+customerId+"";
            preparedStmt = conn.prepareStatement(select);
         
            ResultSet resultSet = preparedStmt.executeQuery(select);
           
            while (resultSet.next()) {              
                if(customerId == resultSet.getLong("id")){
                    blnExist=true;
                }
            }
         
         resultSet.close();
         preparedStmt.close();
         
         this.closeConnection();
        }catch (SQLException sqlE) {
            System.out.println("no exist");
        }
       
        return blnExist;
    }
    /**
     * 
     */
    private void openConnection()   {
       
       try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "abcd*1234");
    
       }
       catch (Exception e){
           System.out.println("No Connec");
       }
    }
    /**
     * 
     * @throws SQLException 
     */
    private void closeConnection() throws SQLException {  
	conn.close();     
    }

    void setAccount(Long customerId, Account account) {
       
       PreparedStatement preparedStmt = null;
       
        try{
            this.openConnection(); 
            
            preparedStmt = conn.prepareStatement("INSERT INTO account VALUES (?,?,?,?,?,?,?)");
            preparedStmt.setLong(1, account.getAccountId());
            preparedStmt.setFloat(2, account.getBalance());
            preparedStmt.setFloat(3, account.getBeginBalance());
            preparedStmt.setTimestamp(4, account.getBeginBalanceTimestamp());
            preparedStmt.setDouble(5, account.getCreditLine());
            preparedStmt.setString(6, account.getDescription());
            preparedStmt.setInt(7, account.getType());
            
            preparedStmt.executeUpdate();
            
            preparedStmt.close();
            this.closeConnection();
         
        } catch (SQLException sqlE) {
            System.out.println("insert failed");
        } catch (Exception e){
            System.out.println("");
        }
       
 }

    
  
}