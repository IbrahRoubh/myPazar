package com.myPazar.service;

import com.myPazar.Tools;
import com.myPazar.model.*;
import com.myPazar.repository.ReceiptRepo;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class ReceiptService {
    private final ReceiptRepo receiptRepo;
    private final Tools tools;

    public ReceiptService(ReceiptRepo receiptRepo, Tools tools) {
        this.receiptRepo = receiptRepo;
        this.tools = tools;
    }

    public Receipt makeReceipt(){
        Customer customer = tools.getAuthenticationCustomer();
        List<CartProduct> cartProducts  = customer.getCart().getCartProducts();

       Receipt receipt = new Receipt(todayDate()
               ,totalPayment(cartProducts)
               , PaymentState.onDoreNotPaid
               , ReceiptState.order
               ,generateTrackCode());
       receipt.setCustomer(customer);
       receipt.setCartProductList(cartProducts);
       receiptRepo.save(receipt);
        return receipt;
    }

    private Date todayDate(){
        long currentTimeMillis = System.currentTimeMillis();
        return new java.sql.Date(currentTimeMillis);
    }

    private double totalPayment(List<CartProduct> cartProducts){
        if(cartProducts==null || cartProducts.size()==0)
            return 0;
        double total=0.0;
        for(CartProduct cartProduct: cartProducts ) {
            total = total + (cartProduct.getProduct().getPrice() * cartProduct.getQuantity());
        }
        total = total+ ((total*5)/100); // tax 5%
        total = total+15; // delivery fee
        return total;
    }

    private String generateTrackCode(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        return  "TRK-" + timestamp + "-" + uniqueId;
    }
}
