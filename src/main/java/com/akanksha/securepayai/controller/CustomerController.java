package com.akanksha.securepayai.controller;

import com.akanksha.securepayai.dto.CustomerRegistrationRequest;
import com.akanksha.securepayai.dto.CustomerResponse;
import com.akanksha.securepayai.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> registerCustomer(@Valid @RequestBody CustomerRegistrationRequest request){

        CustomerResponse response = customerService.registerCustomer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
