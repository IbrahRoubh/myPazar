package com.myPazar.repository;

import com.myPazar.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepo extends JpaRepository<BankCard, Long> {
}
