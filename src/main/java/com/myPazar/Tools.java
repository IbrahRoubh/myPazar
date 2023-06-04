package com.myPazar;

import com.myPazar.model.Customer;
import com.myPazar.model.Seller;
import com.myPazar.repository.CustomerRepo;
import com.myPazar.repository.SellerRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class Tools {
    private final CustomerRepo customerRepo;
    private final SellerRepo sellerRepo;
    public Tools(CustomerRepo customerRepo, SellerRepo sellerRepo) {
        this.customerRepo = customerRepo;
        this.sellerRepo = sellerRepo;
    }

    public Customer getAuthenticationCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return customerRepo.findByEmail(userDetails.getUsername());
    }

    public Seller getAuthenticationSeller(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getAuthorities());
        return sellerRepo.findByEmail(userDetails.getUsername());
    }

    public boolean isValidEmail(String email){
        if(sellerRepo.findByEmail(email) == null && customerRepo.findByEmail(email) == null)
            return true;
        return false;
    }

    public static String uploadDirectory= System.getProperty("user.home") + "\\images";
    public String loadPic(MultipartFile file){

        if(!file.isEmpty()){
            try{
                String originalFilename = file.getOriginalFilename();
                String uniqueFilename = setUniqueName(originalFilename);

                file.transferTo(new File(uploadDirectory+ "/" + uniqueFilename));
                return ("/images/"+uniqueFilename);
            }catch (Exception e){
                return "/images/empty.jpg";
            }
        }else{
            return "/images/empty.jpg";
        }
    }

    private String setUniqueName(String originalFilename){
        String extension = getFileExtension(originalFilename);
        String uniqueId = UUID.randomUUID().toString();
        String timestamp = LocalDateTime.now().toString().replace(":", "-");
        return timestamp + "_" + uniqueId + extension;
    }

    private static String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex != -1) {
            return filename.substring(dotIndex);
        }
        return "";
    }

}
