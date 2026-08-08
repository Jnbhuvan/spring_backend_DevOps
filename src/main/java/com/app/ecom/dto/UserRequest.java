package com.app.ecom.dto;

import com.app.ecom.Model.UserRole;
import lombok.Data;

@Data
public class UserRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String emailAddress;
    private AddressDTO address;
    private UserRole role;
}
