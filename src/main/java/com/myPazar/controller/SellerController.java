package com.myPazar.controller;

import com.myPazar.model.*;
import com.myPazar.repository.CategoryRepo;
import com.myPazar.repository.SpeciesRepo;
import com.myPazar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;
    private final SpeciesRepo speciesRepo;

    public SellerController(SellerService sellerService,SpeciesRepo speciesRepo) {
        this.sellerService = sellerService;
        this.speciesRepo = speciesRepo;
    }
    @GetMapping("/signup")
    public String sellerSignup(){ return "pages/sellerSignup";}

    @PostMapping("/signup")
    public String sellerSignupPost(@ModelAttribute("seller") Seller seller, Model model){
        System.out.println(seller.toString());
        int code = sellerService.addSeller(seller);
        if(code == 0){
            model.addAttribute("emailValid", 0);
            return "pages/sellerSignup";
        }
        return "pages/Login";
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
    public String addProduct(Model model){
        List<ProductUnit> units = Arrays.asList(ProductUnit.values());
        List<String> states = new ArrayList<>();
        for (ProductState state : ProductState.values()) {
            states.add(state.name());
        }
        List<Species> speciesList = speciesRepo.findAll();
        model.addAttribute("units",units);
        model.addAttribute("states",states);
        model.addAttribute("speciesList", speciesList);
        return "pages/sellerAddProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPost(@ModelAttribute("product") Product product, @RequestParam("productPic") MultipartFile pic){
        sellerService.addProduct(product,pic);
        return "redirect:/seller/showProducts";
    }

    @GetMapping("/showProducts")
    public String showProducts(Model model){
        List<Product> products = sellerService.sellerProducts();
        model.addAttribute("products", products);
        return "pages/sellerProductShow";
    }

    @GetMapping("/showOrders")
    public String showOrders(Model model){
        List<ReceiptProduct> receiptProducts = sellerService.getReceiptProductBySellerId();
        model.addAttribute("receiptProducts", receiptProducts);
        return "pages/sellerOrderShow";
    }
}
