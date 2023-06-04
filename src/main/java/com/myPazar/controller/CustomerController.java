package com.myPazar.controller;

import com.myPazar.Tools;
import com.myPazar.model.BankCard;
import com.myPazar.model.Customer;
import com.myPazar.model.Receipt;
import com.myPazar.repository.CustomerRepo;
import com.myPazar.service.CustomerService;
import com.myPazar.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepo customerRepo;
    private final Tools tools;
    private final ReceiptService receiptService;

    public CustomerController(CustomerService customerService, CustomerRepo customerRepo, Tools tools, ReceiptService receiptService) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
        this.tools = tools;
        this.receiptService = receiptService;
    }

    @GetMapping("/login")
    public String customerLogin(){return "pages/Login";}

    @PostMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("error",true);
        return "pages/Login";
    }

    @GetMapping("/signup")
    public String customerSignup(){
        return "pages/customerSignup";
    }

    @PostMapping("/signup")
    public String customerSignupPost(@ModelAttribute("customer") Customer customer, Model model){
        int code = customerService.addCustomer(customer);
        if(code == 1) {
            return "pages/Login";
        }
        else if(code == 0){
            model.addAttribute("emailValid", 0);
            return "pages/Login";
        }
        else{return "";}
    }

//    @PostMapping("/update")
//    public String customerUpdate(@RequestParam("profilePic") MultipartFile file) throws IOException {
//        String picURL= tools.loadPic(file);
//        if(!picURL.equalsIgnoreCase(""))
//        {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            Customer customer=customerRepo.findByEmail(userDetails.getUsername());
//            customer.setProfilePic(picURL);
//        }
//        return "pages/conf";
//    }

    @GetMapping("/settings/profile")
    public String profileShow(Model model){
        Customer customer = tools.getAuthenticationCustomer();
        if(customer.getProfilePic()==null)
        {
            model.addAttribute("pic","/static/Image/userIconProfile.jpg");
        }else {
            if(customer.getProfilePic().isEmpty()||customer.getProfilePic().equalsIgnoreCase("")){
                model.addAttribute("pic","/static/Image/userIconProfile.jpg");
            }else{
                model.addAttribute("pic",customer.getProfilePic());
            }
        }
        model.addAttribute("name",customer.getName());
        model.addAttribute("email",customer.getEmail());
        model.addAttribute("phone",customer.getPhone());
        model.addAttribute("location",customer.getLocation());
        model.addAttribute("part", 1);
        return "pages/userSettings";
    }

    @GetMapping("/settings/payment")
    public String paymentShow(Model model){
        Customer customer=tools.getAuthenticationCustomer();
        BankCard card = customer.getBankCard();
        model.addAttribute("userName",card.getUserName());
        model.addAttribute("cardNum",card.getCardNumber());
        model.addAttribute("cvv",card.getCvv());
        model.addAttribute("expirationDate",card.getExpirationDate());

        model.addAttribute("part", 2);
        return "pages/userSettings";
    }

    @GetMapping("/settings/order")
    public String orderShow(Model model){
        List<Receipt> receipts = receiptService.getOrderedReceipt();
        model.addAttribute("part", 3);
        model.addAttribute("receipts", receipts);
        return "pages/userSettings";
    }

    @GetMapping("/settings/previousOrder")
    public String previousOrderShow(Model model){
        model.addAttribute("part", 4);
        return "pages/userSettings";
    }

    @GetMapping("/settings/setting")
    public String settingShow(Model model){
        Customer customer=tools.getAuthenticationCustomer();
        model.addAttribute("name",customer.getName());
        model.addAttribute("email",customer.getEmail());
        model.addAttribute("phone",customer.getPhone());
        model.addAttribute("location",customer.getLocation());
        model.addAttribute("part", 5);
        return "pages/userSettings";
    }

    @PostMapping("/profile/update")
    public String customerUpdate(@RequestParam("email")String email,
                                 @RequestParam("name")String name,
                                 @RequestParam("phone")String phone,
                                 @RequestParam("location")String location,
                                 Model model
    ){
        Customer customer=tools.getAuthenticationCustomer();
        if(!name.isBlank()){
            customer.setName(name);
        }
        if(!phone.isBlank()){
            customer.setPhone(phone);
        }
        if(!location.isBlank()){
            customer.setLocation(location);
        }
        if(!email.isBlank()&&customerRepo.findByEmail(email)==null){
            customer.setEmail(email);
        }
        model.addAttribute("name", customer.getName());
        model.addAttribute("email", customer.getEmail());
        model.addAttribute("phone", customer.getPhone());
        model.addAttribute("location", customer.getLocation());
        model.addAttribute("part", 5);
        return "pages/userSettings";
    }
}
