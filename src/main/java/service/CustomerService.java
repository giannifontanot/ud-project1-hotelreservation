package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

//All of the service classes use static references to create singleton objects.
public class CustomerService {

    /*
    Define the storage customers by using:
    Set: the entries won't duplicate
    Hash: every entry has a key to access the content
     */

    Collection<Customer> customers = new HashSet<Customer>();

    //Define the Singleton object
    public static CustomerService customerServiceSingleton = null;

    /*
    private and empty constructor. No arguments needed
     */
    private CustomerService(){
    }

    /*
    getInstance() returns the existent object previously created
     */
    public static CustomerService getInstance(){
        if(Objects.isNull(customerServiceSingleton){
            customerServiceSingleton = new CustomerService();
        }
        return customerServiceSingleton;
    }

    /*
    Method to add a customer once the Singleton exists
     */
    public void addCustomer(String email, String name, String lastName){
        Customer newCustomer = new Customer(email, name, lastName);
        customers.add(newCustomer);
    }

    /*
    Returns the customer found using the email
     */
    public Customer getCustomer(String customerEmail){
        for(Customer customer:customers){
            if(customerEmail.equals(customer.getEmail()){
                return customer;
            }
        }
    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }
}
