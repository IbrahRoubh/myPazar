package com.myPazar.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/home")
    public String home() {
        return "pages/home";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){ return "pages/Aboutus"; }
}
