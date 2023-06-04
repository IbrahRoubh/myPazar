package com.myPazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    int sold;
    String pic;
    String description;
    ProductState state;

    @ManyToOne
    Species species;
    @OneToMany(mappedBy = "product")
    List<CartProduct> cartProducts;
    @OneToMany(mappedBy = "product")
    List<ReceiptProduct> receiptProducts;
    @ManyToOne
    Seller seller;

    public Product(String name, double price, String code, String unit, int count, String pic, String description, Species species) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.unit = unit;
        this.count = count;
        this.pic = pic;
        this.description = description;
        this.species = species;
        this.sold = 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                ", unit='" + unit + '\'' +
                ", count=" + count +
                ", sold=" + sold +
                ", pic='" + pic + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", species=" + species.getName() +
                '}';
    }
}
