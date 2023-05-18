package com.myPazar.config;

import com.myPazar.repository.CustomerRepo;
import com.myPazar.repository.SellerRepo;
import com.myPazar.service.detailsService.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
    private final CustomerRepo customerRepo;
    private final SellerRepo sellerRepo;

    @Autowired
    public SecurityConfig(CustomerRepo customerRepo, SellerRepo sellerRepo) {
        this.customerRepo = customerRepo;
        this.sellerRepo = sellerRepo;
    }

    //TODO-1: add for the SecurityFilterChain the authenticated() part
    //        and the roles
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth-> auth.anyRequest().permitAll())
                .formLogin(form -> form.loginPage("/login")
                                .usernameParameter("email-buyer")
                                .passwordParameter("password-buyer")
                                .defaultSuccessUrl("/")
                );
        return http.build();
    }

    //TODO-2: add the database config so the login is connected to database
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomerDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
