package com.safaismail.smartlogistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Delivery {

    // Primary key for each delivery
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer name must not be null, empty or only spaces
    @NotBlank(message = "Customer name is required")
    private String customerName;

    // Address must not be null, empty or only spaces
    @NotBlank(message = "Address is required")
    private String address;

    // Postcode must not be null, empty or only spaces
    @NotBlank(message = "Postcode is required")
    private String postcode;

    // Status must not be null, empty or only spaces
    @NotBlank(message = "Status is required")
    private String status;

    // Empty constructor
    // Required by JPA/Hibernate
    public Delivery() {
    }

    // Constructor with parameters
    // ID is not included because PostgreSQL generates it automatically
    public Delivery(String customerName, String address,
                    String postcode, String status) {

        this.customerName = customerName;
        this.address = address;
        this.postcode = postcode;
        this.status = status;
    }

    // Getter: returns the delivery ID
    public Long getId() {
        return id;
    }

    // Setter: updates the delivery ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter: returns the customer name
    public String getCustomerName() {
        return customerName;
    }

    // Setter: updates the customer name
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter: returns the delivery address
    public String getAddress() {
        return address;
    }

    // Setter: updates the delivery address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter: returns the postcode
    public String getPostcode() {
        return postcode;
    }

    // Setter: updates the postcode
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    // Getter: returns the delivery status
    public String getStatus() {
        return status;
    }

    // Setter: updates the delivery status
    public void setStatus(String status) {
        this.status = status;
    }
}