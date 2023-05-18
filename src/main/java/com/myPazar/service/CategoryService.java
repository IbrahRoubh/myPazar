package com.myPazar.service;

import com.myPazar.model.Category;
import com.myPazar.repository.CategoryRepo;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoriesRepo;

    public List<Category> getAllCategories(){
        List<Category> categories= new ArrayList<>();
        categories=categoriesRepo.findAll();
        return categories;
    }

    public Category getCategoryByName(String categoryName){
        return categoriesRepo.findByName(categoryName);
    }
}
