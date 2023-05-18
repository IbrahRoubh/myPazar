package com.myPazar.service.detailsService;

import com.myPazar.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomerDetails implements UserDetails {

    String email;
    String password;

    private List<GrantedAuthority> authorities;
    public CustomerDetails(Customer customer) {
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        //TODO add the roles
        //this.authorities = Arrays.stream()
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
