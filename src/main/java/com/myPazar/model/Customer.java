package com.myPazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String name;
    String password;
    String phone;
    String location;
    String profilePic;
    //TODO-1 : add the relationship

    @OneToOne
    BankCard bankCard;
    @OneToOne
    Cart cart;
    @OneToMany
    List<Receipt> receipts;
    public Customer(String email, String name, String password, String phone, String location) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.location = location;
    }
}
