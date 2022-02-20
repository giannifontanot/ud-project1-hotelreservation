package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

//All of the service classes use static references to create singleton objects.
public class CustomerService {


    /*
    private and empty constructor. No arguments needed
     */
    private CustomerService(){}

    /*
    Define the storage customers by using:
    Set: the entries won't duplicate
    Hash: every entry has a key to access the content
     */
    Collection<Customer> customersList = new HashSet<Customer>();

    //Define the Singleton object
    public static CustomerService customerServiceSingleton = null;


    /*
     getInstance() returns the existent object previously created
    */
    public static CustomerService getInstance(){
        if(Objects.isNull(customerServiceSingleton)){
            customerServiceSingleton = new CustomerService();
        }
        return customerServiceSingleton;
    }

    /*
    Method to add a customer once the Singleton exists
     */
    public void addCustomer(String name, String lastName, String email){
        Customer newCustomer = new Customer(name, lastName, email);
        customersList.add(newCustomer);
    }

    /*
    Returns the customer found using the email
     */
    public Customer getCustomer(String customerEmail){
        for(Customer customer:customersList){
            if(customerEmail.equals(customer.getEmail())){
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customersList;
    }

}
