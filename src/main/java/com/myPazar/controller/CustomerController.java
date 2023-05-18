package com.myPazar.controller;

import com.myPazar.model.Customer;
import com.myPazar.repository.CustomerRepo;
import com.myPazar.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/login")
    public String customerLogin(){return "pages/Login";}

    @GetMapping("/signup")
    public String customerSignUp(){
        return "pages/";
    }

    @PostMapping("/login")
    public String customerLoginConform(){return "pages/home";}

    @PostMapping("/signup")
    public String signup(@ModelAttribute("customer") Customer customer, Model model){

        if(customerService.addCustomer(customer)) {
            return "";
        }
        else{return "";}
    }
}
