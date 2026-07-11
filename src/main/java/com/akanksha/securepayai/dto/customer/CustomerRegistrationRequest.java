package com.akanksha.securepayai.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationRequest {

    @NotBlank(message = "Customer Name is required")
    private String customerName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;

}
