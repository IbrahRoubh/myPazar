package com.myPazar.controller;

import com.myPazar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;


    @GetMapping("/Login")
    public String sellerLogin(){
        return "pages/";
    }

    @GetMapping("/Signup")
    public String sellerSignUp(){ return "pages/";}

    @GetMapping("")
    public String loadSellers(Model model){

        return "";
    }
}
