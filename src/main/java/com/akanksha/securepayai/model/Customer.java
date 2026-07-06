package com.akanksha.securepayai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull(message = "Customer Name is required")
    private String customerName;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid Email")
    private String email;

    @NotNull
    private String password;

    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;

}
