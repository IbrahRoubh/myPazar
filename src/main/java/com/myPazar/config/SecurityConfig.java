package com.myPazar.config;

import com.myPazar.model.Role;
import com.myPazar.service.detailsService.UserEntityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    //TODO make the authenticated base on the role
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth-> auth.requestMatchers("/seller/signup").permitAll()
                                .requestMatchers("/customer/settings/**","/cart/**","/receipt/**","/seller/**")
                                .authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(form -> form.loginPage("/customer/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(loginSuccessHandler)
                        .failureForwardUrl("/customer/login/error")
                )
                .logout( logout-> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserEntityDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}