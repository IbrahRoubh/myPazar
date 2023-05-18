package com.myPazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    //TODO-1 : add the relationship


    public Customer(String email, String name, String password, String phone, String location) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.location = location;
    }
}
