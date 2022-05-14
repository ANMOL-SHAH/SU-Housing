package com.example.API.Services;

import com.example.API.Model.Role;
import com.example.API.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInterface {
    String getTest();
    List<User> listAll();
    ResponseEntity<String> getUserDetails(String id);
    ResponseEntity<String> deleteUser(String email);
    ResponseEntity<String> deleteAllUser();
    ResponseEntity<String> updateUser(User user);
    ResponseEntity<?> getUserByRole(Role role);
    ResponseEntity<String> getRoleByEmail(String email);
}
