package com.myPazar.controller;

import com.myPazar.Tools;
import com.myPazar.model.BankCard;
import com.myPazar.model.Customer;
import com.myPazar.repository.CustomerRepo;
import com.myPazar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private Tools tools;


    @GetMapping("/login")
    public String customerLogin(){return "pages/Login";}

    @PostMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("error",true);
        return "pages/Login";
    }

    @GetMapping("/signup")
    public String customerSignUp(){
        return "pages/";
    }

    @PostMapping("/login")
    public String customerLoginConform(){return "pages/home";}

    @PostMapping("/signup")
    public String signup(@ModelAttribute("customer") Customer customer, Model model){
        if(customerService.addCustomer(customer)) {
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
        model.addAttribute("part", 3);
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
