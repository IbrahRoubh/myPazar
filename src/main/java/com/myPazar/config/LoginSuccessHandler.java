package com.myPazar.config;

import com.myPazar.model.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    // TODO config it so it take the preveus url
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String url = request.getContextPath();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        if(user.getAuthorities().contains(new SimpleGrantedAuthority(Role.SELLER.toString())))
            url+= "/seller/home";
        else if(user.getAuthorities().contains(new SimpleGrantedAuthority(Role.CUSTOMER.toString())))
            url+= "/";
        response.sendRedirect(url);
    }
}
