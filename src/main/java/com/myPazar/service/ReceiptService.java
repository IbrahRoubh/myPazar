package com.myPazar.service;

import com.myPazar.Tools;
import com.myPazar.model.*;
import com.myPazar.repository.*;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReceiptService {
    private final ReceiptRepo receiptRepo;
    private final Tools tools;
    private final CustomerRepo customerRepo;
    private final CartRepo cartRepo;
    private final CartProductRepo cartProductRepo;
    private final ReceiptProductRepo receiptProductRepo;
    private final ProductRepo productRepo;
    public ReceiptService(ReceiptRepo receiptRepo, Tools tools
            , CustomerRepo customerRepo, CartRepo cartRepo
            , CartProductRepo cartProductRepo, ReceiptProductRepo receiptProductRepo
            ,ProductRepo productRepo
    ) {
        this.receiptRepo = receiptRepo;
        this.tools = tools;
        this.customerRepo = customerRepo;
        this.cartRepo = cartRepo;
        this.cartProductRepo = cartProductRepo;
        this.receiptProductRepo = receiptProductRepo;
        this.productRepo = productRepo;
    }

    public Receipt makeReceipt(){
        Customer customer = tools.getAuthenticationCustomer();
        List<CartProduct> cartProducts  = customer.getCart().getCartProducts();
        if(cartProducts==null ||cartProducts.size()==0){
            return null;
        }
       Receipt receipt = new Receipt(todayDate()
               ,totalPayment(cartProducts)
               , PaymentState.onDoreNotPaid
               , ReceiptState.order
               ,generateTrackCode());
       receipt.setCustomer(customer);
       receiptRepo.save(receipt);
       List<Receipt> receipts= customer.getReceipts();
       receipts.add(receipt);
       customer.setReceipts(receipts);
       customerRepo.save(customer);
       for(CartProduct cartProduct: cartProducts){
           ReceiptProduct receiptProduct = new ReceiptProduct();
           receiptProduct.setReceipt(receipt);
           receiptProduct.setProduct(cartProduct.getProduct());
           receiptProduct.setQuantity(cartProduct.getQuantity());
           receiptProductRepo.save(receiptProduct);
           cartProductRepo.delete(cartProduct);

           Product product = cartProduct.getProduct();
           product.setCount(product.getCount()-cartProduct.getQuantity());
           product.setSold(product.getSold()+cartProduct.getQuantity());
           productRepo.save(product);
       }
        Cart cart = customer.getCart();
        cart.getCartProducts().clear();
        cartRepo.save(cart);
       receiptRepo.save(receipt);
        return receipt;
    }

    private Date todayDate(){
        long currentTimeMillis = System.currentTimeMillis();
        return new java.sql.Date(currentTimeMillis);
    }

    public double totalPayment(List<CartProduct> cartProducts){
        if(cartProducts==null || cartProducts.size()==0)
            return 0;
        double total=0.0;
        for(CartProduct cartProduct: cartProducts ) {
            total = total + (cartProduct.getProduct().getPrice() * cartProduct.getQuantity());
        }
        total = total+ ((total*5)/100); // tax 5%
        total = total+15; // delivery fee
        total = Math.round(total * 100) / 100.0;
        return total;
    }

    private String generateTrackCode(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        return  "TRK-" + timestamp + "-" + uniqueId;
    }

    public Receipt getByTrackCode(String trackCode){
        return receiptRepo.getByTrackCode(trackCode);
    }

    public List<Receipt> getOrderedReceipt(){
        Customer customer = tools.getAuthenticationCustomer();
        List<Receipt> receipts = customer.getReceipts();
        return receipts.stream()
                .filter(rs -> rs.getState() == ReceiptState.order || rs.getState() == ReceiptState.onWay)
                .collect(Collectors.toList());
    }
}
