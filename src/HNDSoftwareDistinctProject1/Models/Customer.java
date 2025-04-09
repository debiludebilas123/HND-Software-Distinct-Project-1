package HNDSoftwareDistinctProject1.Models;

import java.util.UUID;

public class Customer {
    private String customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Customer(String customerID, String firstName, String lastName, String email, String phone, String address) {
        this.customerID = (customerID != null) ? customerID : "CUS-" + UUID.randomUUID().toString().substring(0, 10);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
