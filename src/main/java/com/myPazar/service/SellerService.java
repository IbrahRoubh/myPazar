package com.myPazar.service;

import com.myPazar.Tools;
import com.myPazar.model.Product;
import com.myPazar.model.ReceiptState;
import com.myPazar.model.Role;
import com.myPazar.model.Seller;
import com.myPazar.repository.ReceiptProductRepo;
import com.myPazar.repository.SellerRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    private final SellerRepo sellerRepo;
    private final ReceiptProductRepo receiptProductRepo;
    private final PasswordEncoder encoder;
    private final Tools tools;
    public SellerService(SellerRepo sellerRepo, PasswordEncoder encoder, ReceiptProductRepo receiptProductRepo, Tools tools) {
        this.sellerRepo = sellerRepo;
        this.encoder = encoder;
        this.receiptProductRepo = receiptProductRepo;
        this.tools = tools;
    }

    public List<Seller> getSellers(){
        List<Seller> sellers= new ArrayList<>();
        return sellers;
    }

    public List<Product> findMostSoldProductsBySellerId(){
        Seller seller = tools.getAuthenticationSeller();
        List<Product> products = receiptProductRepo.findMostSoldProductsBySellerId(seller.getId(), ReceiptState.canceled);
        return products;
    }

    public double totalPrice(List<Product> products){
        double total = 0.0;
        for (Product product: products)
            total+= product.getPrice()*product.getSold();
        return Math.round(total * 100.0) / 100.0;
    }

    public int addSeller(Seller seller){
        if(tools.isValidEmail(seller.getEmail()))
            return 0;
        seller.setRole(Role.SELLER.toString());
        seller.setPassword(encoder.encode(seller.getPassword()));
        sellerRepo.save(seller);
        return 1;
    }

    public List<Product> sellerProducts(){
        Seller seller = tools.getAuthenticationSeller();
        return seller.getProducts();
    }
}
