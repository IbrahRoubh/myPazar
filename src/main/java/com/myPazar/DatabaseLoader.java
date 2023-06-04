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
    private final CartRepo cartRepo;
    private final SellerRepo sellerRepo;
    @Autowired
    public DatabaseLoader(CategoryRepo categoriesRepo, SpeciesRepo speciesRepo, CustomerRepo customerRepo, PasswordEncoder encoder,ProductRepo productRepo,BankCardRepo bankCardRepo, CartRepo cartRepo, SellerRepo sellerRepo)
    {
        this.categoriesRepo= categoriesRepo;
        this.speciesRepo= speciesRepo;
        this.customerRepo= customerRepo;
        this.encoder = encoder;
        this.productRepo = productRepo;
        this.bankCardRepo = bankCardRepo;
        this.cartRepo = cartRepo;
        this.sellerRepo = sellerRepo;
    }
    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category("fruit");
        Category category2 = new Category("vegetables");
        Category category3 = new Category("Diary");
        Category category4 = new Category("Spices");
        category1.setPic("/images/fruit.jpg");
        category2.setPic("/images/clothes.jpg");
        category3.setPic("/images/milk.jpg");
        category4.setPic("/images/spices.jpg");
        categoriesRepo.save(category1);
        categoriesRepo.save(category2);
        categoriesRepo.save(category3);
        categoriesRepo.save(category4);

        Species species1 = new Species("apple", category1);
        Species species2 = new Species("orange", category1);
        Species species3 = new Species("cheese", category3);
        Species species4 = new Species("banana", category1);
        Species species5 = new Species("pineapple", category1);
        Species species6 = new Species("Cherry", category1);
        Species species7 = new Species("lemon", category1);
        Species species8 = new Species("watermelon", category1);
        Species species9 = new Species("Fig", category1);
        Species species10 = new Species("cucumber", category2);
        Species species11 = new Species("carrot", category2);
        Species species12 = new Species("onion", category2);
        Species species13 = new Species("tomato", category2);
        Species species14 = new Species("potato", category2);
        Species species15 = new Species("Milk" , category3);
        Species species16 = new Species("Butter" , category3);
        Species species17 = new Species("Yogurt" , category3);
        Species species18 = new Species("Cumin" , category4);
        Species species19 = new Species("Cinnamon", category4);

        speciesRepo.save(species1);
        speciesRepo.save(species2);
        speciesRepo.save(species3);
        speciesRepo.save(species4);
        speciesRepo.save(species5);
        speciesRepo.save(species6);
        speciesRepo.save(species7);
        speciesRepo.save(species8);
        speciesRepo.save(species9);
        speciesRepo.save(species10);
        speciesRepo.save(species11);
        speciesRepo.save(species12);
        speciesRepo.save(species13);
        speciesRepo.save(species14);
        speciesRepo.save(species15);
        speciesRepo.save(species16);
        speciesRepo.save(species17);
        speciesRepo.save(species18);
        speciesRepo.save(species19);

        Seller seller = new Seller("a101@gmail.com","A101",encoder.encode("000"),"0501427741620","istanboul");
        sellerRepo.save(seller);

        Product product1 = new Product("turkish banana",15,"osa11",ProductUnit.Kg.toString(),20,"","first class banana",species4);
        Product product2 = new Product("italian banana",25.49,"ib21",ProductUnit.Kg.toString(),12,"","fresh banana from italy",species4 );
        Product product3 = new Product("yellow apple",7.85,"ay",ProductUnit.Kg.toString(),5,"","yellow apple ",species1);
        Product product4 = new Product("red apple",6,"ga",ProductUnit.Kg.toString(),24,"","",species1);
        Product product5 = new Product("green apple",12,"ed",ProductUnit.Kg.toString(),32,"","green apple",species1);
        Product product6 = new Product("Heirloom Tomato",12,"erfsdd",ProductUnit.Kg.toString(),38,"/images/Heirloom Tomato.jpg","Heirloom Tomato",species13);
        Product product7 = new Product("Celebrity Tomato",12,"erdfd",ProductUnit.Kg.toString(),38,"/images/Celebrity Tomato.jpg","Celebrity Tomato",species13);
        Product product8 = new Product("Turkish Tomato",15,"fvfc",ProductUnit.Kg.toString(),66,"/images/Turkish tomato.jpg","Turkish Tomato",species13);
        Product product9 = new Product("Roma Tomato",18,"fvfkjvc",ProductUnit.Kg.toString(),66,"/images/Roma tomato.jpg","Roma Tomato",species13);
        product1.setPic("/images/turkishBanana.jpg");
        product2.setPic("/images/italianBanana.jpg");
        product3.setPic("/images/yellowApple.jpg");
        product4.setPic("/images/redApple.jpg");
        product5.setPic("/images/greenApple.jpg");

        product1.setSeller(seller);
        product2.setSeller(seller);
        product3.setSeller(seller);
        product4.setSeller(seller);
        product5.setSeller(seller);
        product6.setSeller(seller);
        product7.setSeller(seller);
        product8.setSeller(seller);
        product9.setSeller(seller);

        product1.setState(ProductState.Active);
        product2.setState(ProductState.Active);
        product3.setState(ProductState.Active);
        product4.setState(ProductState.Active);
        product5.setState(ProductState.Active);
        product6.setState(ProductState.Active);
        product7.setState(ProductState.Active);
        product8.setState(ProductState.Active);
        product9.setState(ProductState.Active);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);
        productRepo.save(product4);
        productRepo.save(product5);
        productRepo.save(product6);
        productRepo.save(product7);
        productRepo.save(product8);
        productRepo.save(product9);


        Customer customer1 = new Customer("i@gmail","ibrahim", encoder.encode("123"),"04140","istanboul");
        Customer customer2 = new Customer("f@gmail","ibrahim", encoder.encode("000"),"04140","istanboul");
        customer1.addRole(Role.CUSTOMER);
        customer2.addRole(Role.CUSTOMER);
        customerRepo.save(customer1);
        customerRepo.save(customer2);


        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cart1.setCustomer(customer1);
        cart2.setCustomer(customer2);
        cartRepo.save(cart1);
        cartRepo.save(cart2);

        BankCard bankCard1= new BankCard("ibrahim","1447 1471 1474 0021","020","07/29",customer1);
        BankCard bankCard2= new BankCard(customer2.getName(),"1447 1471 1474 0021","017","06/27",customer2);
        bankCardRepo.save(bankCard1);
        bankCardRepo.save(bankCard2);

        customer1.setBankCard(bankCard1);
        customer2.setBankCard(bankCard2);
        customer1.setCart(cart1);
        customer2.setCart(cart2);
        customerRepo.save(customer1);
        customerRepo.save(customer2);

    }
}
