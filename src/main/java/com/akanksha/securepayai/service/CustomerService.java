package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.CustomerLoginRequest;
import com.akanksha.securepayai.dto.CustomerLoginResponse;
import com.akanksha.securepayai.dto.CustomerRegistrationRequest;
import com.akanksha.securepayai.dto.CustomerRegistrationResponse;
import com.akanksha.securepayai.exception.CustomerAlreadyExistsException;
import com.akanksha.securepayai.exception.CustomerNotFoundException;
import com.akanksha.securepayai.model.Customer;
import com.akanksha.securepayai.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // tells springboot to create the constructor for the customer repo
//modern way to create it, else @autowired will work for same.
public class CustomerService{

    private final CustomerRepository customerRepository;
    public CustomerRegistrationResponse registerCustomer(CustomerRegistrationRequest request){
      if(customerRepository.existsByEmail(request.getEmail())){
          throw new CustomerAlreadyExistsException("Customer already exists");
      }

      Customer customer = mapToCustomer(request);
      Customer savedCustomer = customerRepository.save(customer);

      CustomerRegistrationResponse response = new CustomerRegistrationResponse();
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

    public CustomerLoginResponse loginCustomer(CustomerLoginRequest request){
        Customer customer = customerRepository.findByEmail(request.getEmail()).orElseThrow(() -> new CustomerNotFoundException("Invalid Email or Password"));
        CustomerLoginResponse response = new CustomerLoginResponse();
        response.setCustomerId(customer.getCustomerId());
        response.setCustomerName(customer.getCustomerName());
        response.setEmail(customer.getEmail());

        if(!customer.getPassword().equals(request.getPassword())){
            throw new CustomerNotFoundException("Invalid Email or Password");
        }
        return response;
    }


}
