package com.myPazar.service;


import com.myPazar.Tools;
import com.myPazar.model.Cart;
import com.myPazar.model.Customer;
import com.myPazar.repository.CartRepo;
import com.myPazar.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.tools.Tool;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final   PasswordEncoder encoder;
    private final CartRepo cartRepo;
    private final Tools tools;

    public CustomerService(CustomerRepo customerRepo, PasswordEncoder encoder, CartRepo cartRepo, Tools tools) {
        this.customerRepo = customerRepo;
        this.encoder = encoder;
        this.cartRepo = cartRepo;
        this.tools = tools;
    }

    public int addCustomer(Customer customer){
        if(!tools.isValidEmail(customer.getEmail()))
            return 0;

        customer.setPassword(encoder.encode(customer.getPassword()));
        customerRepo.save(customer);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartRepo.save(cart);
        customer.setCart(cart);
        Customer customer1 = customerRepo.save(customer);
        if(customer1 !=null){
            return 1;
        }else{
            return 2;
        }
    }
}
