package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;

//All of the service classes use static references to create singleton objects.
public class CustomerService {

    Collection<Customer> customers = new HashSet<Customer>();

    //Define the Singleton
    public static CustomerService customerServiceSingleton = null;

    CustomerService(){

    }

    public void addCustomer(String email, String name, String lastName){
        Customer newCustomer = new Customer(email, name, lastName);
        customers.add(newCustomer);
    }

    public Customer getCustomer(String customerEmail){
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }
}
