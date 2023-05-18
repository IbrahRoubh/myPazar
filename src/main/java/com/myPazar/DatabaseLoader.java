package com.myPazar;

import com.myPazar.model.Category;
import com.myPazar.model.Customer;
import com.myPazar.model.Species;
import com.myPazar.repository.CategoryRepo;
import com.myPazar.repository.CustomerRepo;
import com.myPazar.repository.SpeciesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner{
    private final CategoryRepo categoriesRepo;
    private final SpeciesRepo speciesRepo;
    private final CustomerRepo customerRepo;

    private final PasswordEncoder encoder;
    @Autowired
    public DatabaseLoader(CategoryRepo categoriesRepo, SpeciesRepo speciesRepo, CustomerRepo customerRepo, PasswordEncoder encoder)
    {
        this.categoriesRepo= categoriesRepo;
        this.speciesRepo= speciesRepo;
        this.customerRepo= customerRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category("fruit");
        Category category2 = new Category("clothes");
        Category category3 = new Category("milk");
        Category category4 = new Category("vage");
        Category category5 = new Category("v1");
        Category category6 = new Category("v2");
        Category category7 = new Category("v3");

        categoriesRepo.save(category1);
        categoriesRepo.save(category2);
        categoriesRepo.save(category3);
        categoriesRepo.save(category4);
        categoriesRepo.save(category5);
        categoriesRepo.save(category6);
        categoriesRepo.save(category7);

        Species species1 = new Species("apple", category1);
        Species species2 = new Species("orange", category1);
        Species species3 = new Species("chess", category3);
        Species species4 = new Species("banana", category1);
        Species species5 = new Species("pineapple", category1);

        speciesRepo.save(species1);
        speciesRepo.save(species2);
        speciesRepo.save(species3);
        speciesRepo.save(species4);
        speciesRepo.save(species5);

        Customer customer1 = new Customer("i@gmail","ibrahim", encoder.encode("123"),"04140","istanboul");
        Customer customer2 = new Customer("f@gmail","ibrahim", encoder.encode("000"),"04140","istanboul");

        customerRepo.save(customer1);
        customerRepo.save(customer2);
    }
}
