package com.myPazar.service.detailsService;

import com.myPazar.model.Customer;
import com.myPazar.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String customerEmail) throws UsernameNotFoundException {
        Optional<Customer> customer= Optional.of(customerRepo.findByEmail(customerEmail));
        return customer.map(CustomerDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("customer not fount: "+customerEmail));
    }
}
