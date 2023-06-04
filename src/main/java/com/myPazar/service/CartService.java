package com.myPazar.service;

import com.myPazar.Tools;
import com.myPazar.model.Cart;
import com.myPazar.model.CartProduct;
import com.myPazar.model.Customer;
import com.myPazar.model.Product;
import com.myPazar.repository.CartProductRepo;
import com.myPazar.repository.CartRepo;
import com.myPazar.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepo cartRepo;
    private final Tools tools;
    private final CartProductRepo cartProductRepo;
    private final ProductRepo productRepo;
    public CartService(CartRepo cartRepo,Tools tools, CartProductRepo cartProductRepo, ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.tools = tools;
        this.cartProductRepo = cartProductRepo;
        this.productRepo = productRepo;
    }
    private Cart getCustomerAuthCart(){
        Customer customer = tools.getAuthenticationCustomer();
        return customer.getCart();
    }

    public boolean addTOCart(Product product, int quantity){
        if(product.getCount()>=quantity){
            Cart cart = getCustomerAuthCart();
            List<CartProduct> cartProducts = cart.getCartProducts();
            if(cartProducts == null){
                cartProducts = new ArrayList<>();
            }

            CartProduct cartProduct = new CartProduct();
            cartProduct.setQuantity(quantity);
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProductRepo.save(cartProduct);

            cartProducts.add(cartProduct);
            cartRepo.save(cart);
            return true;
        }else{
            return false;
        }
    }

    public List<CartProduct> getCartProducts(){
        Cart cart = getCustomerAuthCart();
        List<CartProduct> cartProducts = cart.getCartProducts();
        return cartProducts;
    }

    public void deleteCartProduct(Long id){
        CartProduct cartProduct = cartProductRepo.getReferenceById(id);
        Cart cart = getCustomerAuthCart();
        cart.getCartProducts().remove(cartProduct);
        cartProductRepo.deleteById(id);
    }
}
