package com.myPazar.controller;

import com.myPazar.model.CartProduct;
import com.myPazar.model.Product;
import com.myPazar.repository.ProductRepo;
import com.myPazar.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductRepo productRepo;

    public CartController(CartService cartService, ProductRepo productRepo) {
        this.cartService = cartService;
        this.productRepo = productRepo;
    }

    @PostMapping("/addToCart")
    public String addTOCart(Model model, @RequestParam("productId") Long productId, @RequestParam("quantity") int quantity){
        Product product = productRepo.getReferenceById(productId);
        Boolean isAdd = cartService.addTOCart(product, quantity);
        model.addAttribute("isAdd",isAdd);
        model.addAttribute("product",product);
        return "pages/productShow";
    }


    @GetMapping("/showCart")
    public String showCart(Model model){
        List<CartProduct> cartProducts = cartService.getCartProducts();
        model.addAttribute("cartProducts",cartProducts);
        return "pages/cartShow";
    }

    @PostMapping("/productDelete")
    public String cartProductDelete(@ModelAttribute("cartProductId")Long id){
        cartService.deleteCartProduct(id);
        return "redirect:/cart/showCart";
    }
}
