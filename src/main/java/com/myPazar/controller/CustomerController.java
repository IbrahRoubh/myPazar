package com.myPazar.controller;

import com.myPazar.Tools;
import com.myPazar.model.Customer;
import com.myPazar.repository.CustomerRepo;
import com.myPazar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/customer")

public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private Tools tools;


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

    @PostMapping("/update")
    public String customerUpdate(@RequestParam("profilePic") MultipartFile file) throws IOException {
        String picURL= tools.loadPic(file);
        if(!picURL.equalsIgnoreCase(""))
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Customer customer=customerRepo.findByEmail(userDetails.getUsername());
            customer.setProfilePic(picURL);
        }
        return "pages/conf";
    }
}
