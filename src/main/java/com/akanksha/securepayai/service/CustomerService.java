package com.akanksha.securepayai.service;

import com.akanksha.securepayai.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // tells springboot to create the constructor for the customer repo
//modern way to create it else @autowired will work for same.
public class CustomerService {

    private final CustomerRepository customerRepository;
}
