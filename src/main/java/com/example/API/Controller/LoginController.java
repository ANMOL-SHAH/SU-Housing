package com.example.API.Controller;

import com.example.API.Model.User;
import com.example.API.Repo.UserRepo;
import com.example.API.Services.LoginService;
import com.example.API.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestParam(name="email") String email, @RequestParam(name="password") String password){
        return loginService.loginUser(email,password);
    }
}
