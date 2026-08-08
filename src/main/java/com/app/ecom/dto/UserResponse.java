package com.app.ecom.dto;

import com.app.ecom.Model.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String emailAddress;
    private UserRole role;
    private AddressDTO address;
}
