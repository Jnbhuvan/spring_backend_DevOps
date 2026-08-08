package com.app.ecom.Controller;


import com.app.ecom.Model.User;
import com.app.ecom.Services.UserServices;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

//    @GetMapping("/getusers")
//    public ResponseEntity<List<User>> getUsers(){
//
//        return new ResponseEntity<>(userServices.getUsers(), HttpStatus.OK);
//    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){

        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable int userid){
        User user = userServices.getUserById(userid);
        if(user == null){
            return new ResponseEntity<>("User Not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userServices.addUser(user);
        return ResponseEntity.ok("User Added Successfully!");
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        userServices.deleteUser(userId);
        return ResponseEntity.ok("User Deleted Successfully!");

    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userData){
        User user = userServices.updateUser(userId, userData);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
