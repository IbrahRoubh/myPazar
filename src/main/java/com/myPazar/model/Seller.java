package com.myPazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String name;
    String password;
    String phone;
    String location;
    String profilePic;
    String role;
    String workPermit;

    @OneToMany(mappedBy = "seller")
    List<Product> products;

    public Seller(String email, String name, String password, String phone, String location) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.location = location;
        this.role = Role.SELLER.toString();
    }

    public void addProduct(Product product){
        if(this.products==null)
            this.products = new ArrayList<>();
        this.products.add(product);
    }
}
