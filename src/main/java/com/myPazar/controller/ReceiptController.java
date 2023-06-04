package com.myPazar.controller;

import com.myPazar.Tools;
import com.myPazar.model.CartProduct;
import com.myPazar.model.Customer;
import com.myPazar.model.Receipt;
import com.myPazar.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final Tools tools;

    public ReceiptController(ReceiptService receiptService, Tools tools) {
        this.receiptService = receiptService;
        this.tools = tools;
    }

    @GetMapping("/conformOrder")
    public String conformOrder(Model model){
        Customer customer = tools.getAuthenticationCustomer();
        List<CartProduct> cartProducts = customer.getCart().getCartProducts();
        double totalPayment = receiptService.totalPayment(cartProducts);
        model.addAttribute("customer",customer);
        model.addAttribute("cartProducts",cartProducts);
        model.addAttribute("totalPayment",totalPayment);
        return "pages/orderConfirmation";
    }

    @PostMapping("/makeReceipt")
    public String makeReceipt(Model model){
        Receipt receipt = receiptService.makeReceipt();
        if(receipt==null){
            //TODO : add fail page
            return "pages/";
        }
        model.addAttribute("receipt",receipt);
        return "pages/orderSuccess";
    }

    @GetMapping("/showReceipt/{receiptTrackCode}")
    public String showReceipt(Model model, @PathVariable("receiptTrackCode")String trackCode){
        Receipt receipt = receiptService.getByTrackCode(trackCode);
        model.addAttribute("receipt", receipt);
        return "pages/receipt";
    }
}
