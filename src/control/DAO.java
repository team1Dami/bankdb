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

/**
 * @author Saray
 */
public class DAO {
    
    /**
     * Attributes
     */
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStmt;
      
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
   
    Account getAccountData(long AccountId) {
       Account ret = new Account();
       try{
            this.openConnection();
            
            statement = connection.createStatement();
            
            String select = "select * from Account where id = "+AccountId+"";
            ResultSet resultSet = statement.executeQuery(select);
		
		while(resultSet.next()) {
                    ret.setAccountId(AccountId);
                    ret.setBalance(resultSet.getFloat("balance"));
                    ret.setBeginBalance(resultSet.getFloat("beginBalance"));
                    ret.setBeginBalanceTimestamp(resultSet.getFloat("beginBalanceTimestamp"));
                    ret.setCreditLine(resultSet.getDouble("creditLine"));
                    ret.setDescription(resultSet.getString("description"));
                    ret.setType(resultSet.getInt("type"));
		}
		resultSet.close();
            
            this.closeConnection();   
       }
       catch(SQLException sqlE){
            System.out.println("Account can not found or doesn't exist");
       }
       catch(Exception e){
            System.out.println("No se ha podido establecer conexión con la base de datos");
       }    
        
      
       return ret;
    }
    public void createMovement(long accountId) throws Exception{
        System.out.println("Estoy en el DAO");
        boolean esta=estaIdAccount(accountId);
        System.out.println("He salido de la funcion");
        if(esta){
            System.out.println("Se ha encontrado la cuenta ");
            Movement ret = new Movement();
            ret.setDatos(accountId);
            try{
                 this.openConnection();

                 statement = connection.createStatement();

                 String select = "insert into  Movement values('"+ret.getMovementId()+"','"+ret.getAmount()+"','"+ret.getBalance()+"','"+ret.getDescription()+"','"+ret.getTimestamp()+"','"+ret.getAccountId()+"')";
                 ResultSet resultSet = statement.executeQuery(select);

                     while(resultSet.next()) {

                     }
                     resultSet.close();

                 this.closeConnection();   
            }
            catch(SQLException sqlE){
                 System.out.println("Account can not found or doesn't exist");
            }
            catch(Exception e){
                 System.out.println("No se ha podido establecer conexión con la base de datos");
            }    

 
        }else{
            System.out.println("No se ha encontrado la id de la cuenta");
        }
       
        
    }
    public boolean estaIdAccount(long idAccount) throws Exception{
        System.out.println("Estoy en la funcion estaIdAccount");
        this.openConnection();
        boolean esta=false;
        statement =  connection.createStatement();
            
            String select = "select id from account where id is "+idAccount;
            ResultSet resultSet = statement.executeQuery(select);
            System.out.println("Estoy para entrar en el while");
            while(resultSet.next()) {
                    if(resultSet.getLong(1)==idAccount){
                        esta = true;
                        break;
                    }
		}
        
        this.closeConnection();
        return esta;
    }
}
