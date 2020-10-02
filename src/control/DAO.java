/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Saray
 */
public class DAO {
    
    /**
     * Attributes
     */
    private Connection conn;
    private Statement statement;
    private PreparedStatement preparedStmt = null;
      
    /**
     * 
     * @param customerId 
     * @return Customer to consult the datas
     */
   public  Customer getCustomerData(long customerId){
       Customer ret = new Customer();
       try{
            this.openConnection();
            
            statement = conn.createStatement();
            
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
   
   private void openConnection()   {
       
       try {
      
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "abcd*1234");
    
       }
       catch (Exception e){
           System.out.println("No Connec");
       }
    }
   
   private void closeConnection() throws SQLException {  
	conn.close();     
    }
   
   
   
    public boolean getCustomerId(Long customerId) {
       PreparedStatement preparedStmt = null;
       boolean blnExist = false;
       
       try{
           this.openConnection();         
            String select = "select id from customer where id = "+customerId+";";
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
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return blnExist;
    }




    void createCustomer(Customer cus) {
        try {
            this.openConnection();
            
            String insert = "insert into customer values("+cus.getCustomerId()+",'"+cus.getCity()+"','"+cus.getEmail()+
                            "','"+cus.getFirstName()+"','"+cus.getLastName()+"','"+cus.getMiddleInitial()+"',"+
                            cus.getPhone()+",'"+cus.getState()+"','"+cus.getStreet()+"',"+
                            cus.getZip()+")";
            statement.executeUpdate(insert);
            
        this.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Insert fallida");
        } catch (Exception ex) {
            System.out.println("Conexion fallida");
        }
    }

}
