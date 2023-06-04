package com.myPazar.service.detailsService;

import com.myPazar.model.Customer;
import com.myPazar.model.Role;
import com.myPazar.model.Seller;
import com.myPazar.repository.CustomerRepo;
import com.myPazar.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;


public class UserEntityDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(email);
        Seller seller = sellerRepo.findByEmail(email);

        if (customer != null) {
            return new CustomerDetails(customer);
        } else if (seller != null) {
            return new SellerDetails(seller);
        }

        throw new UsernameNotFoundException("User not found: " + email);
    }
}