package com.myPazar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProductController {
    @GetMapping("/products")
    public String product(){
        return "pages/product";
    }
}
