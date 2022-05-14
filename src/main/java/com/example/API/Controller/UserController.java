package com.example.API.Controller;

import com.example.API.Model.Role;
import com.example.API.Model.User;
import com.example.API.Repo.UserRepo;
import com.example.API.Services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired(required = true)
    UserServices userServices;

    //Get Request to list all users
    @GetMapping("/allUsers")
    @ResponseBody
    public List<User> getUser(){
        return userServices.listAll();
    }

    //Get request to retrieve user by id
    @GetMapping("/user")
    public ResponseEntity<String> getUserDetails(@RequestParam String id) {
            return userServices.getUserDetails(id);
    }

    //delete all users
    @DeleteMapping("/deleteAllUser")
    public ResponseEntity<String> deleteAllUser(){
        return userServices.deleteAllUser();
    }

    //delete user by id
    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable("email") String email) {
        return userServices.deleteUser(email);
    }

    //update user
    @PutMapping(value = "/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        return userServices.updateUser(user);
    }

    //Get User by Role
    @GetMapping("/userByRole")
    public ResponseEntity<?> userByRole(@RequestParam("Role") Role role){
        System.out.println(role);
        return userServices.getUserByRole(role);
    }

    //Get Role by email
    @GetMapping("/userByEmail")
    public ResponseEntity<String> roleByEmail(@RequestParam("email") String email){
        return userServices.getRoleByEmail(email);
    }
}

