package com.myPazar.service;

import com.myPazar.model.Product;
import com.myPazar.model.Species;
import com.myPazar.repository.ProductRepo;
import com.myPazar.repository.SpeciesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SpeciesRepo speciesRepo;

    public List<Product> getProductsBySpeciesName(String speciesName){
        Species species= speciesRepo.findByName(speciesName);

        if(species!= null){
        return species.getProductList();
        }else{
            return null;
        }
    }
}
