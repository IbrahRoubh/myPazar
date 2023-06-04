package com.myPazar.service;

import com.myPazar.Tools;
import com.myPazar.model.*;
import com.myPazar.repository.ProductRepo;
import com.myPazar.repository.ReceiptProductRepo;
import com.myPazar.repository.SellerRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    private final SellerRepo sellerRepo;
    private final ReceiptProductRepo receiptProductRepo;
    private final PasswordEncoder encoder;
    private final Tools tools;
    private final ProductRepo productRepo;
    public SellerService(SellerRepo sellerRepo, PasswordEncoder encoder, ReceiptProductRepo receiptProductRepo, Tools tools, ProductRepo productRepo) {
        this.sellerRepo = sellerRepo;
        this.encoder = encoder;
        this.receiptProductRepo = receiptProductRepo;
        this.tools = tools;
        this.productRepo = productRepo;
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
        if(!tools.isValidEmail(seller.getEmail()))
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

    public double totalSold(double price,int sold){
        double total = price*sold;
        return Math.round(total * 100.0) / 100.0;
    }

    public void addProduct(Product product, MultipartFile pic){
        product.setSeller(tools.getAuthenticationSeller());
        String newPicURLName = tools.loadPic(pic);
        product.setPic(newPicURLName);
        productRepo.save(product);
    }

    public List<ReceiptProduct> getReceiptProductBySellerId(){
        Seller seller = tools.getAuthenticationSeller();
        List<ReceiptProduct> receiptProducts = receiptProductRepo.findReceiptProductsBySellerId(seller.getId(), ReceiptState.canceled);
        return receiptProducts;
    }
}
