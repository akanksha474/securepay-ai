package com.akanksha.securepayai.dto;

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
    private String customerName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;

}
