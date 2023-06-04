package com.myPazar.controller;


import com.myPazar.Tools;
import com.myPazar.model.Seller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    Tools tools;

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request)throws ServletException {
        request.logout();
        return "redirect:/home";
    }

    @GetMapping("/sellerInfo")
    public String sellerInfo(){

        return "pages/sellerInfo";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword(){return "pages/forgetPassword"; }

    @GetMapping("/test")
    public String test(){
        Seller seller =tools.getAuthenticationSeller();
        System.out.println(seller.getRole());
        return "pages/sellerAddProduct";}
}
