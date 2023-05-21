package com.myPazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    double price;
    String code;
    String unit;
    int count;
    String pic;
    String description;

    @ManyToOne
    Species species;

    public Product(String name, double price, String code, String unit, int count, String pic, String description, Species species) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.unit = unit;
        this.count = count;
        this.pic = pic;
        this.description = description;
        this.species = species;
    }
}
