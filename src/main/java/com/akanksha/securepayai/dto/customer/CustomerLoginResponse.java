package com.akanksha.securepayai.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoginResponse {

    private Long customerId;
    private String customerName;
    private String email;

}
