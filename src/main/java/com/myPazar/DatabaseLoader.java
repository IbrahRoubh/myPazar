package com.myPazar;

import com.myPazar.model.*;
import com.myPazar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner{
    private final CategoryRepo categoriesRepo;
    private final SpeciesRepo speciesRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private final BankCardRepo bankCardRepo;
    private final PasswordEncoder encoder;
    @Autowired
    public DatabaseLoader(CategoryRepo categoriesRepo, SpeciesRepo speciesRepo, CustomerRepo customerRepo, PasswordEncoder encoder,ProductRepo productRepo,BankCardRepo bankCardRepo)
    {
        this.categoriesRepo= categoriesRepo;
        this.speciesRepo= speciesRepo;
        this.customerRepo= customerRepo;
        this.encoder = encoder;
        this.productRepo = productRepo;
        this.bankCardRepo = bankCardRepo;
    }
    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category("fruit");
        Category category2 = new Category("clothes");
        Category category3 = new Category("milk");
        category1.setPic("/images/fruit.jpg");
        category2.setPic("/images/clothes.jpg");
        category3.setPic("/images/milk.jpg");

        categoriesRepo.save(category1);
        categoriesRepo.save(category2);
        categoriesRepo.save(category3);

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

        Product product1 = new Product("turkish banana",15,"osa11","kg",20,"","first class onion",species4);
        Product product2 = new Product("italian banana",25.49,"ib21","kg",12,"","fresh banana from italy",species4 );
        Product product3 = new Product("yellow apple",7.85,"ay","kg",5,"","as djf ldj dlfjd ldjfd dlfjd ldjdld dldfjd ",species1);
        Product product4 = new Product("red apple",6,"ga","kg",24,"","",species1);
        Product product5 = new Product("green apple",12,"ed","kg",32,"","green apple",species1);

        product1.setPic("/images/turkishBanana.jpg");
        product2.setPic("/images/italianBanana.jpg");
        product3.setPic("/images/yellowApple.jpg");
        product4.setPic("/images/redApple.jpg");
        product5.setPic("/images/greenApple.jpg");



        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);
        productRepo.save(product4);
        productRepo.save(product5);

        Customer customer1 = new Customer("i@gmail","ibrahim", encoder.encode("123"),"04140","istanboul");
        Customer customer2 = new Customer("f@gmail","ibrahim", encoder.encode("000"),"04140","istanboul");

        customerRepo.save(customer1);
        customerRepo.save(customer2);

        BankCard bankCard1= new BankCard("ibrahim","1447 1471 1474 0021","020","07/29",customer1);
        BankCard bankCard2= new BankCard(customer2.getName(),"1447 1471 1474 0021","017","06/27",customer2);

        bankCardRepo.save(bankCard1);
        bankCardRepo.save(bankCard2);

        customer1.setBankCard(bankCard1);
        customer2.setBankCard(bankCard2);

        customerRepo.save(customer1);
        customerRepo.save(customer2);
    }
}
