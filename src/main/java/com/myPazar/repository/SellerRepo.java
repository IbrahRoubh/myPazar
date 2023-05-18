package com.myPazar.repository;

import com.myPazar.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller, Long> {
    Seller findByEmail(String sellerEmail);
}
