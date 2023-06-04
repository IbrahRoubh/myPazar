package com.myPazar.repository;

import com.myPazar.model.Product;
import com.myPazar.model.ReceiptProduct;
import com.myPazar.model.ReceiptState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptProductRepo extends JpaRepository<ReceiptProduct, Long> {
    @Query("SELECT rp.product " +
            "FROM ReceiptProduct rp " +
            "JOIN rp.receipt r " +
            "WHERE rp.product.seller.id = :sellerId " +
            "AND r.state != :receiptState " +
            "GROUP BY rp.product " +
            "ORDER BY SUM(rp.quantity) DESC")
    List<Product> findMostSoldProductsBySellerId(@Param("sellerId") Long sellerId, @Param("receiptState") ReceiptState receiptState);

}
