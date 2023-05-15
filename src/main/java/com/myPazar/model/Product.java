package com.myPazar.model;

import lombok.Data;

@Data
public class Product {
    Long id;
    String name;
    double price;
    String code;
    String unit;
    int count;
}
