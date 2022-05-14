package com.example.API.Services;

import org.springframework.http.ResponseEntity;

public interface LoginServiceInterface {
    ResponseEntity<String> loginUser(String email, String password);
}
