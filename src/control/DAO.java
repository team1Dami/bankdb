/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Account;
import clases.Customer;o
import clases.Movement;

import clases.CustomerAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Statement;
import java.util.ArrayList;
import static javafx.beans.binding.Bindings.select;

/**
 * @author Saray
 */
public class DAO {
    
    /**
     * Attributes
     */

    private Connection connection=null;
    private Statement statement=null;
    private PreparedStatement preparedStmt=null;
   
    
    /**
     * getting the properties file datas
    
        private ResourceBundle configFile;
        private String driverDB;
        private String urlDB;
        private String userDB;
        private String passwordDB; 
    /**
     * 
     * @throws Exception 
     
      public DAO() throws Exception {
      this.configFile = ResourceBundle.getBundle("control.configFile");
      this.driverDB = this.configFile.getString("Driver");
      this.urlDB = this.configFile.getString("Conn");
      this.userDB = this.configFile.getString("DBUser");
      this.passwordDB = this.configFile.getString("DBPass"); 
    }
    */

    /**
     * 
     * @param customerId 
     * @return Customer to consult the datas
     */
   public  Customer getCustomerData(long customerId){
       Customer ret = new Customer();     
       try{
          
           this.openConnection();         
            String select = "select * from customer where id = "+customerId+"";
            preparedStmt = connection.prepareStatement(select);
         
           try (ResultSet resultSet = preparedStmt.executeQuery(select)) {
               
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
           }
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

    public ArrayList <Account> getCustomerAccount(long customerId){
        
        ArrayList <Account> cuentas = new ArrayList();
                      
        try{   
            this.openConnection();
            
            String select = "SELECT a.* from account a, customer_account ca where ca.customers_id = "+customerId+" and ca.accounts_id = a.id;";

            preparedStmt = connection.prepareStatement(select);

            ResultSet resultSet = null;
            resultSet = preparedStmt.executeQuery(select);
             
            
                while (resultSet.next()) {

                  Account aux = new Account();
                  aux.setAccountId(resultSet.getLong("id"));
                  aux.setBalance(resultSet.getFloat("balance"));
                  aux.setBeginBalance(resultSet.getFloat("beginBalance"));
                  aux.setBeginBalanceTimestamp(resultSet.getTimestamp("beginBalanceTimestamp"));
                  aux.setCreditLine(resultSet.getDouble("creditLine"));
                  aux.setDescription(resultSet.getString("description"));
                  aux.setType(resultSet.getInt("type"));
                
                cuentas.add(aux);             
            }
                
            preparedStmt.close();
         
            this.closeConnection();
        
        } catch(SQLException sqlE){
            
            System.out.println("Customer can not found or doesn't exist");
            }
            catch(Exception e){
                 System.out.println("No se ha podido establecer conexión con la base de datos");
            }  
       return cuentas;
    }
   
   public ArrayList <Movement> getAccountMovemnt(long accountId) throws Exception{
        ArrayList <Movement> movimientos = new ArrayList();
        
        ResultSet resultset = null;
        PreparedStatement preparedStmt = null;
        
        try{
        this.openConnection();
        String select = "select * from movement where account_id = "+accountId+"";
        preparedStmt = connection.prepareStatement(select);
        
        ResultSet resultSet = preparedStmt.executeQuery(select);
        while(resultSet.next()){
            Movement movimiento = new Movement();
            movimiento.setAccountId(accountId);
            movimiento.setAmount(resultSet.getFloat("amount"));
            movimiento.setBalance(resultSet.getFloat("balance"));
            movimiento.setDescription(resultSet.getString("description"));
            movimiento.setMovementId(resultSet.getInt("id"));
            movimiento.setTimestamp(resultSet.getTimestamp("timestamp"));
            movimientos.add(movimiento);
        }
        //resultSet.close();
        preparedStmt.close();
        this.closeConnection();
        
        }catch (SQLException e){
            
        }
        return movimientos;
   }
   
   private void openConnection() throws Exception {
	
        //Class.forName("com.mysql.cj.jdbc.Driver");
        String path = "jdbc:mysql://localhost:3306/bankdb";
	      connection = DriverManager.getConnection(path, "root", "");
      
    }
   /**
    * 
    * @param customerId
    * @return true if exist or false if doesn't exist
     * @throws java.lang.Exception
    */
    
   

   private void closeConnection() throws SQLException {
	//statement.close();
	    connection.close();
   }
  
  public boolean getCustomerId(Long customerId) throws Exception{
       
    boolean blnExist = false;
       
       try{
           this.openConnection();         
            String select = "select id from customer where id = "+customerId+"";
            preparedStmt = connection.prepareStatement(select);
         
           try (ResultSet resultSet = preparedStmt.executeQuery(select)) {
               while (resultSet.next()) {
                   if(customerId == resultSet.getLong("id")){
                       blnExist=true;
                   }
               }
           }
         preparedStmt.close();
         
         this.closeConnection();
        }catch (SQLException sqlE) {
            System.out.println("no exist");
        }
       
        return blnExist;
    }

    public void setAccount(Long customerId, Account account) {
        
        try{
            this.openConnection(); 
            
            preparedStmt = connection.prepareStatement("INSERT INTO account VALUES (?,?,?,?,?,?,?)");
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
    /**
     * 
     * @param customerAccount 
     */
    public void setCustomerAccount(CustomerAccount customerAccount) {       
       
        try{
            this.openConnection(); 
            
            preparedStmt = connection.prepareStatement("INSERT INTO customer_account VALUES (?,?)");
            preparedStmt.setLong(1, customerAccount.getCustomerId());
            preparedStmt.setFloat(2, customerAccount.getAccountId());
            
            preparedStmt.executeUpdate();
            
            preparedStmt.close();
            this.closeConnection();
         
        } catch (SQLException sqlE) {
            System.out.println("insert failed");
        } catch (Exception e){
            System.out.println("");
        }
    }

     /**
     * 
     */
    private void openConnection()   {
       
       try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "abcd*1234");
    
       }
       catch (ClassNotFoundException | SQLException e){
           System.out.println("No Connec");
       }
    }
    /**
     * 
     * @throws SQLException 
     */
    private void closeConnection() throws SQLException {  
	    connection.close();     
    }

    boolean accountExist(long accountId) {
        boolean blnExist = false;
       
       try{
           this.openConnection();         
            String select = "select id from account where id = "+accountId+"";
            preparedStmt = connection.prepareStatement(select);
         
           try (ResultSet resultSet = preparedStmt.executeQuery(select)) {
               while (resultSet.next()) {
                   if(accountId == resultSet.getLong("id")){
                       blnExist=true;
                   }
               }
           }
         preparedStmt.close();
         
         this.closeConnection();
         
        }catch (SQLException sqlE) {   
            System.out.println("Exist");
        }
       
        return blnExist;
    }
  
  public void setCustomerAccount(long customerId, long accountId){
          PreparedStatement preparedStmt = null;
        try {
            this.openConnection();
           preparedStmt = connection.prepareStatement("INSERT INTO customer_account VALUES (?,?)");
            preparedStmt.setLong(1, customerId);
            preparedStmt.setLong(2, accountId);
            
            preparedStmt.executeUpdate();
            
            preparedStmt.close();
            
        this.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Insert fallida");
        } catch (Exception ex) {
            System.out.println("Conexion fallida");
        }
    }    
  
      public boolean getAccountId(Long accId){
        PreparedStatement preparedStmt = null;
       boolean blnExist = false;
       
       try{
           this.openConnection();         
            String select = "select id from account where id = "+accId+";";
            preparedStmt = connection.prepareStatement(select);
         
            ResultSet resultSet = preparedStmt.executeQuery(select);
           
            while (resultSet.next()) {              
                if(accId == resultSet.getLong("id")){
                    System.out.println("El id introducido ya esta registrado");
                    blnExist=true;
                }
            }
         
         resultSet.close();
         preparedStmt.close();
         
         this.closeConnection();
        }catch (SQLException sqlE) {
            System.out.println("no exist");
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return blnExist;
    }
   void createCustomer(Customer cus) {
        PreparedStatement preparedStmt = null;
        try {
            this.openConnection();
            
            preparedStmt = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?,?)");
            preparedStmt.setLong(1, cus.getCustomerId());
            preparedStmt.setString(2, cus.getCity());
            preparedStmt.setString(3, cus.getEmail());
            preparedStmt.setString(4,cus.getFirstName());
            preparedStmt.setString(5, cus.getLastName());
            preparedStmt.setString(6, cus.getMiddleInitial());
            preparedStmt.setFloat(7, cus.getPhone());
            preparedStmt.setString(8, cus.getState());
            preparedStmt.setString(9, cus.getStreet());
            preparedStmt.setFloat(10, cus.getZip());
            preparedStmt.executeUpdate();
            
            preparedStmt.close();
            
        this.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Insert fallida");
        } catch (Exception ex) {
            System.out.println("Conexion fallida");
        }
    }
}

