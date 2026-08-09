package com.app.ecom.Services;

import com.app.ecom.Model.Address;
import com.app.ecom.Model.User;
import com.app.ecom.Model.UserRole;
import com.app.ecom.Repo.UserRepo;
import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {

    UserRepo userRepo;

    public UserServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    public List<User> getUsers() {
//        return userRepo.findAll();
//    }

    public List<UserResponse> getAllUsers() {

        return userRepo.findAll().stream().
                map(this::mapToUserResponse) //similar to map(user -> mapToUserResponse(user))
                .collect(Collectors.toList());

    }

    public UserResponse getUserById(int userid) {

        return mapToUserResponse(userRepo.findById(userid).orElse(null));
        //return userRepo.findById(userid).orElse(null);

    }

    public void addUser(UserRequest userRequest) {
        User user = new User();

        updateUserFromRequest(user, userRequest);

        userRepo.save(user);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmailAddress(userRequest.getEmailAddress());
        user.setPhoneNo(userRequest.getPhoneNo());
        user.setRole(UserRole.CUSTOMER);
        if(userRequest.getAddress() != null){
            Address address = new Address();

            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setStreet(userRequest.getAddress().getStreet());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        };

    }

    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }

    public UserResponse updateUser(int userId, UserRequest userData){
        User user = userRepo.findById(userId).orElse(null);
        if(user == null){
            return null;
        }
//        user.setFirstName(userData.getFirstName());
//        user.setLastName(userData.getLastName());
//        user.setPhoneNo(userData.getPhoneNo());
//        user.setEmailAddress(userData.getEmailAddress());

        updateUserFromRequest(user, userData);
        userRepo.save(user);
        return mapToUserResponse(user);


    }

    private UserResponse mapToUserResponse(User user){ //for Get request
        UserResponse userResponse = new UserResponse();

        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());

        userResponse.setPhoneNo(user.getPhoneNo());
        userResponse.setEmailAddress(user.getEmailAddress());
        userResponse.setRole(UserRole.CUSTOMER);

        if(user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResponse.setAddress(addressDTO);
        }

        return userResponse;

    }
}

