package com.myPazar.service;

import com.myPazar.model.Category;
import com.myPazar.model.Species;
import com.myPazar.repository.CategoryRepo;
import com.myPazar.repository.SpeciesRepo;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {
    private final SpeciesRepo speciesRepo;
    private final CategoryRepo categoryRepo;
    @Autowired
    public SpeciesService(SpeciesRepo speciesRepo, CategoryRepo categoryRepo) {
        this.speciesRepo = speciesRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<Species> getSpeciesByCategoryName(String categoryName){
        Category category= categoryRepo.findByName(categoryName);
        if(category != null){
            return category.getSpeciesList();
        }else{
            return null;
        }
    }
}
