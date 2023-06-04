package com.myPazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date date;
    double totalPrice;
    PaymentState paymentStatus;
    ReceiptState state;
    String trackCode;
    Date dateOFDelivery;

    @ManyToOne
    Customer customer;
    @OneToMany(mappedBy = "receipt", fetch = FetchType.EAGER)
    List<ReceiptProduct> receiptProducts;

    public Receipt(Date date, double totalPrice, PaymentState paymentStatus, ReceiptState state, String trackCode) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
        this.state = state;
        this.trackCode = trackCode;
    }
}
