package com.myPazar.controller;

import com.myPazar.model.Product;
import com.myPazar.model.Seller;
import com.myPazar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {
    SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }
    @GetMapping("/signup")
    public String sellerSignup(){ return "pages/sellerSignup";}

    @PostMapping("/signup")
    public String sellerSignupPost(@ModelAttribute("seller") Seller seller, Model model){
        int code = sellerService.addSeller(seller);
        if(code == 0){
            model.addAttribute("emailValid", 0);
            return "pages/sellerSignup";
        }
        return "pages/loginSeller";
    }

    @GetMapping("/home")
    public String homeSeller(Model model){
        List<Product> products = sellerService.findMostSoldProductsBySellerId();
        double total = sellerService.totalPrice(products);
        model.addAttribute("products",products.subList(0, Math.min(products.size(), 3)));
        model.addAttribute("total", total);
        return "pages/sellerDashboard";
    }

    @GetMapping("/addProduct")
    public String addProduct(){return "pages/sellerAddProduct";}

    @GetMapping("/showProducts")
    public String showProducts(Model model){
        List<Product> products = sellerService.sellerProducts();
        model.addAttribute("products", products);
        return "pages/sellerProductShow";
    }
}
