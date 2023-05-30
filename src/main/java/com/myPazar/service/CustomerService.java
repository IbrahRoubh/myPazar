package com.myPazar.service;


import com.myPazar.model.Cart;
import com.myPazar.model.Customer;
import com.myPazar.repository.CartRepo;
import com.myPazar.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private  PasswordEncoder encoder;
    private CartRepo cartRepo;

    public boolean isValidEmail(String email){
        if(customerRepo.findByEmail(email) ==null){
            return true;
        }else{
            return false;
        }
    }

    public boolean addCustomer(Customer customer){
        customer.setPassword(encoder.encode(customer.getPassword()));
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartRepo.save(cart);
        customer.setCart(cart);
        if(customerRepo.save(customer) !=null){
            return true;
        }else{
            return false;
        }
    }
}
