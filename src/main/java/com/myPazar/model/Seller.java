package com.myPazar.model;

import lombok.Data;

@Data
public class Seller {
    Long id;
    String email;
    String name;
    String password;
    String phone;
    String location;

    //TODO : add pic and work permit
}
