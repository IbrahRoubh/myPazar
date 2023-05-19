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

    @ManyToOne
    Species species;
    //TODO-1 : add the relationship
}
