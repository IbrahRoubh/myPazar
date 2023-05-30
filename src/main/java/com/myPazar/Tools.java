package com.myPazar;

import com.myPazar.model.Customer;
import com.myPazar.repository.CustomerRepo;
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
    CustomerRepo customerRepo;

    public Tools(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer getAuthenticationCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return customerRepo.findByEmail(userDetails.getUsername());
    }


    public String loadPic(MultipartFile file){

        if(!file.isEmpty()){
            try{
                String originalFilename = file.getOriginalFilename();
                String uniqueFilename = setUniqueName(originalFilename);

                //TODO: we should change the path passe on the localhost location
                String uploadDirectory = "C:/Users/ss/Desktop/myPazar project/pics/";
                file.transferTo(new File(uploadDirectory + uniqueFilename));
                return (uploadDirectory+uniqueFilename);
            }catch (Exception e){
                return "";
            }
        }else{
            return "";
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
