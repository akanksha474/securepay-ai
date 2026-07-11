package com.akanksha.securepayai.repository;

import com.akanksha.securepayai.enums.AccountType;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    boolean existsByCustomerAndAccountType(Customer customer, AccountType accountType);

}
