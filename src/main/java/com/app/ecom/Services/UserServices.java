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

    public User getUserById(int userid) {
        return userRepo.findById(userid).orElse(null);
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
        if(user.getAddress() != null){
            Address address = new Address();

            address.setCity(user.getAddress().getCity());
            address.setState(user.getAddress().getState());
            address.setCountry(user.getAddress().getCountry());
            address.setStreet(user.getAddress().getStreet());
            address.setZipcode(user.getAddress().getZipcode());
            user.setAddress(address);
        };
    }

    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }

    public User updateUser(int userId, User userData){
        User user = userRepo.findById(userId).orElse(null);
        if(user == null){
            return null;
        }
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setPhoneNo(userData.getPhoneNo());
        user.setEmailAddress(userData.getEmailAddress());
        userRepo.save(user);
        return user;

    }

    private UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();

        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());

        userResponse.setPhoneNo(user.getPhoneNo());
        userResponse.setEmailAddress(user.getEmailAddress());


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

