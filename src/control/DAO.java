/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Account;
import clases.Customer;
import clases.Movement;
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
   
   public ArrayList <Account> getCustomerAccount(long customerId){
       System.out.println("Estoy en el DAO");
        ArrayList <Account> cuentas = new ArrayList();
        ResultSet resultset = null;
        PreparedStatement preparedStmt = null;
        Account cuenta = null;
        try{
            this.openConnection();
            
            String select = "SELECT a.* from account a, customer_account ca where ca.customers_id = "+customerId+" and ca.accounts_id = a.id;";
            preparedStmt = connection.prepareStatement(select);
            ResultSet resultSet = preparedStmt.executeQuery(select);
            while(resultSet.next()){
                cuenta.setAccountId(resultSet.getLong("id"));
                cuenta.setBalance(resultSet.getFloat("balance"));
                cuenta.setBeginBalance(resultSet.getFloat("beginBalance"));
                cuenta.setBeginBalanceTimestamp(resultSet.getTimestamp("beginBalanceTimestamp"));
                cuenta.setCreditLine(resultSet.getDouble("creditLine"));
                cuenta.setDescription(resultSet.getString("description"));
                cuenta.setType(resultSet.getInt("type"));
                cuentas.add(cuenta);
            }
            preparedStmt.close();
            resultSet.close();
            this.closeConnection(); 
        }
            catch(SQLException sqlE){
            System.out.println("Customer can not found or doesn't exist");
            }
            catch(Exception e){
                 System.out.println("No se ha podido establecer conexión con la base de datos");
            }  
       return cuentas;
    }
   
   public ArrayList <Movement> getAccountMovemnt(long accountId) throws Exception{
        ArrayList <Movement> movimientos = new ArrayList();
        Movement movimiento = null;
        ResultSet resultset = null;
        PreparedStatement preparedStmt = null;
        
        try{
        this.openConnection();
        String select = "select * from movement where account_id = "+accountId+"";
        //preparedStmt = connection.prepareStatement(select);
        
        ResultSet resultSet = preparedStmt.executeQuery(select);
        while(resultSet.next()){
            movimiento.setAccountId(accountId);
            movimiento.setAmount(resultSet.getFloat("amount"));
            movimiento.setBalance(resultSet.getFloat("balance"));
            movimiento.setDescription(resultSet.getString("description"));
            movimiento.setMovementId(resultSet.getInt("id"));
            movimiento.setTimestamp(resultSet.getTimestamp("timestamp"));
            movimientos.add(movimiento);
        }
        resultSet.close();
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
   
   private void closeConnection() throws SQLException {
	statement.close();
	connection.close();
        
        System.out.println("Connection has been closed");
    }
}
