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

    @GetMapping("/FAQ")
    public String FAQ(){return "pages/faq";}
}
