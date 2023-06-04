package com.myPazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String name;
    String password;
    String phone;
    String location;
    String profilePic;
    private Set<Role> roles;

    @OneToOne
    BankCard bankCard;
    @OneToOne
    Cart cart;
    @OneToMany
    List<Receipt> receipts;

    public Customer(String email, String name, String password, String phone, String location) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.location = location;
        this.roles = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", cart=" + cart.getId() +
                '}';
    }

    public void addRole(Role role) {
        if(this.roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
