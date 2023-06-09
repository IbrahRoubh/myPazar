package com.myPazar.controller;

import com.myPazar.model.Product;
import com.myPazar.repository.ProductRepo;
import com.myPazar.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{speciesName}/products")
    public String product(@PathVariable String speciesName, Model model){
        List<Product> products=productService.getProductsBySpeciesName(speciesName);
        if(products!=null)
        model.addAttribute("products",products);
        return "pages/product";
    }

    @GetMapping("/product/{productName}/{productId}")
    public String productShow(@PathVariable String productName, @PathVariable String productId, Model model) {
//        String decodedProductId = UriUtils.decode(productId, "UTF-8");
        long pId = Long.parseLong(productId);
        Product product = productService.getProductById(pId);
        model.addAttribute("product", product);
        return "pages/productShow";
    }
}
