package com.myPazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    //TODO-1: add pic
    @ManyToOne
    Category category;
    @OneToMany(mappedBy = "species")
    List<Product> productList;

    public Species(String name, Category category){
        this.name= name;
        this.category= category;
    }
}