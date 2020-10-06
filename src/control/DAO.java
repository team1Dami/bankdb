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
        boolean esta=estaIdAccount(accountId);
        if(esta){
            System.out.println("Se ha encontrado la cuenta ");
            Movement ret = new Movement();
            ret.setDatos(accountId);
            boolean existe=estaIdMovement(ret.getMovementId());
            if(existe){
                System.out.println("Ese movimiento ya existe");
            }else{
                  try{
                 this.openConnection();

                 preparedStmt = connection.prepareStatement("insert into  Movement values (?,?,?,?,?,?)");

                  preparedStmt.setLong(1, ret.getMovementId());
                  preparedStmt.setDouble(2, ret.getAmount());
                  preparedStmt.setDouble(3, ret.getBalance());
                  preparedStmt.setString(4, ret.getDescription());
                  preparedStmt.setTimestamp(5, ret.getTimestamp());
                  preparedStmt.setLong(6, ret.getAccountId());
                  preparedStmt.executeUpdate();
                  preparedStmt.close();

                 this.closeConnection();   
            }
            catch(SQLException sqlE){
                 System.out.println("Account can not found or doesn't exist");
            }
            catch(Exception e){
                 System.out.println("No se ha podido establecer conexión con la base de datos");
            } 
            }
             

 
        }else{
            System.out.println("No se ha encontrado la id de la cuenta");
        }
       
        
    }
    public boolean estaIdAccount(long idAccount) throws Exception{
        this.openConnection();
        boolean esta=false;
        statement =  connection.createStatement();
            
            String select = "select id from account where id = "+idAccount;
            ResultSet resultSet = statement.executeQuery(select);
            while(resultSet.next()) {
                    if(resultSet.getLong(1)==idAccount){
                        esta = true;
                        break;
                    }
		}
        
        this.closeConnection();
        return esta;
    }
       public boolean estaIdMovement(long idMovement) throws Exception{
        this.openConnection();
        boolean esta=false;
        statement =  connection.createStatement();
            
            String select = "select id from movement where id = "+idMovement;
            ResultSet resultSet = statement.executeQuery(select);
            while(resultSet.next()) {
                    if(resultSet.getLong(1)==idMovement){
                        esta = true;
                        break;
                    }
		}
        
        this.closeConnection();
        return esta;
    }
}
