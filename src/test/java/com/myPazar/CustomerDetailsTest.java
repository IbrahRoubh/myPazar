package com.myPazar;


import com.myPazar.model.Customer;
import com.myPazar.model.Role;
import com.myPazar.service.detailsService.CustomerDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class CustomerDetailsTest {

    @Test
    public void testGetAuthorities() {
        // Create a Customer with a role
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPassword("password");
        customer.addRole(Role.CUSTOMER);

        // Create a CustomerDetails instance
        CustomerDetails customerDetails = new CustomerDetails(customer);

        // Get the authorities from CustomerDetails
        Collection<? extends GrantedAuthority> authorities = customerDetails.getAuthorities();

        // Ensure the authorities contain a single SimpleGrantedAuthority with the correct role name
        SimpleGrantedAuthority expectedAuthority = new SimpleGrantedAuthority(Role.CUSTOMER.toString());
        Assertions.assertEquals(Collections.singleton(expectedAuthority), authorities);
    }
}