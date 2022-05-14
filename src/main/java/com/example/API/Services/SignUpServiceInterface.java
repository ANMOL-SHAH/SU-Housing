package com.example.API.Services;

import com.example.API.Model.Role;
import com.example.API.Model.User;
import org.springframework.http.ResponseEntity;

public interface SignUpServiceInterface {
    ResponseEntity<String> signUpUser(User user);
}
