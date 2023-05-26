package com.myPazar.controller;

import com.myPazar.model.Category;
import com.myPazar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoriesService;

    @GetMapping("/categories")
    public String categoriesLoad(Model model){
        List<Category> categoriesList = categoriesService.getAllCategories();
        model.addAttribute("categoriesList",categoriesList);
        System.out.println(categoriesList.get(0).getPic());

        return "pages/category";
    }

}
