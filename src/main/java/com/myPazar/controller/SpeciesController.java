package com.myPazar.controller;

import com.myPazar.model.Species;
import com.myPazar.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class SpeciesController {
    @Autowired
    SpeciesService speciesService;
    @GetMapping("/{categoryName}")
    public String loadSpecies(Model model, @PathVariable String categoryName){
        List<Species> speciesList = speciesService.getSpeciesByCategoryName(categoryName);
        if(speciesList != null){model.addAttribute("speciesList",speciesList);}
        return "pages/Species";
    }
}
