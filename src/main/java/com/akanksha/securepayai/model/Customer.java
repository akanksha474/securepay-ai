package com.akanksha.securepayai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long customerID;

    @NotNull(message = "Customer Name is required")
    public String customerName;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid Email")
    public String email;

    @NotNull
    public String password;

    public String phoneNumber;
    public String dateOfBirth;
    public String address;


}
