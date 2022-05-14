package com.example.API.Services;

import com.example.API.Model.User;
import com.example.API.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements LoginServiceInterface{
    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<String> loginUser(String email, String password){
        List<User> users = userRepo.findAll();
        for(User u:users){
            System.out.println(u.getEmail()+u.getPassword());
            if(email != null && password != null ){
                if(u.getEmail().equals(email)){
                    if(u.getPassword().equals(password)){
                        return new ResponseEntity<>("User Found Successfully!", HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>("Password Incorrect!", HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }
        return new ResponseEntity<>("!", HttpStatus.BAD_REQUEST);
    }
}
