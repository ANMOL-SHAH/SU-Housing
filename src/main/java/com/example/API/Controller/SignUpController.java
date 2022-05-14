package com.example.API.Controller;

import com.example.API.Model.Role;
import com.example.API.Model.User;
import com.example.API.Services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestParam String fName, @RequestParam String lName, @RequestParam String email,
                                           @RequestParam Role role, @RequestParam String password, @RequestParam String phone){

    //@PostMapping("/signup")
    //public ResponseEntity<String> saveUser(@Valid @RequestBody User user){
        //System.out.println("Test Message"+signUpService.signUpUser(user));
        User user = new User(fName, lName, email, role, password, phone);
        System.out.println(user.toString());
        return signUpService.signUpUser(user);
    }
}
