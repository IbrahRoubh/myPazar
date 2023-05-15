package com.myPazar.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = {"/home","/"})
    public String home() {
        return "pages/home";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){ return "pages/Aboutus"; }

    @GetMapping("/contactUs")
    public String contactUs(){return "pages/contactUs";}

    @GetMapping("/sellerOrBuyer")
    public String sellerOrBuyer(){return "pages/sellerOrBuyer";}

    @GetMapping("/Customer/Login")
    public String customerLogin(){return "pages/";}

    @GetMapping("/Customer/Signup")
    public String customerSignUp(){
        return "pages/";
    }

    @GetMapping("/Seller/Login")
    public String sellerLogin(){
        return "pages/";
    }

    @GetMapping("/Seller/Signup")
    public String sellerSignUp(){ return "pages/";}
}
