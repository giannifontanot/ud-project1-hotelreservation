package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
private String firstName;
private String lastName;
private String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        //Verify email using a regex
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)com$");
        Matcher matcher = emailPattern.matcher(email);

        try {
            if (!matcher.find()) {
                throw new IllegalArgumentException("Email address is not valid");
            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
