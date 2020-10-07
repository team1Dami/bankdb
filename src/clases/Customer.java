/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import utilities.Util;
/**
 *
 * @author Saray
 */
public class Customer implements Serializable{
    
    /**
     * Attributes
     * */
        
    private long customerId;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String city;
    private String street;
    private String state;
    private String email;
    private long phone;
    private int zip;
    
    /**
     * Constructor without args
     */
    public Customer() { 
    }
    
    /**
     * Method to obtain Customer data
     */
    public void getCustomerData(){
        
        System.out.println("Customer Data:");
        System.out.println("Name: "+firstName+" "+lastName);
        System.out.println("Middle Initial: "+middleInitial);
        System.out.println("Phone and Email: "+phone+" "+email);
        System.out.println("Address: "+street+" "+city+" "+zip+" "+state);
    }
    
    /**
     * Set customer data
     */
    public void setCustomerData() {
        
    }
    
    /**
     * @return the customerId
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleInitial
     */
    public String getMiddleInitial() {
        return middleInitial;
    }

    /**
     * @param middleInitial the middleInitial to set
     */
    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public long getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(long phone) {
        this.phone = phone;
    }

    /**
     * @return the zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(int zip) {
        this.zip = zip;
    }
    
    public void createCustomer(long id){
        customerId=id;
    }

    public void setDatos() {
        customerId = Util.leerLong("Introduce tu id de cliente: ");
        firstName = Util.introducirCadena("Introduce tu primer nombre: ");
        lastName = Util.introducirCadena("Introduce tu ultimo nombre: ");
        middleInitial= Util.introducirCadena("Introduce tu middleintial: ");
        street = Util.introducirCadena("Introduce la calle: ");
        city = Util.introducirCadena("Introduce la ciudad: ");
        phone = Util.leerLong("Introduce tu telefono ");
        zip = Util.leerInt("Introduce tu zip");
        email =Util.introducirCadena("Introduce email");
         
    }

}
