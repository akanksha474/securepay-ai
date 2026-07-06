package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.CustomerRegistrationRequest;
import com.akanksha.securepayai.dto.CustomerResponse;
import com.akanksha.securepayai.model.Customer;
import com.akanksha.securepayai.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // tells springboot to create the constructor for the customer repo
//modern way to create it, else @autowired will work for same.
public class CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerResponse registerCustomer(CustomerRegistrationRequest request){
      if(customerRepository.existsByEmail(request.getEmail())){
          throw new RuntimeException("Customer already exists");
      }

      Customer customer = mapToCustomer(request);
      Customer savedCustomer = customerRepository.save(customer);

      CustomerResponse response = new CustomerResponse();
      response.setCustomerId(savedCustomer.getCustomerId());
      response.setCustomerName(savedCustomer.getCustomerName());
      response.setEmail(savedCustomer.getEmail());

      return response;
    }

    private Customer mapToCustomer(CustomerRegistrationRequest request){
        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
        customer.setDateOfBirth(request.getDateOfBirth());
        return customer;
    }
}
