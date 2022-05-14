package com.example.API.Services;

import com.example.API.Model.Role;
import com.example.API.Model.User;
import com.example.API.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Valid;

@Service
public class SignUpService implements SignUpServiceInterface {
    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<String> signUpUser(User userRec){
        try {
            System.out.println(userRec.getRole());
            if (userRec.getEmail() != null) {
                for (User user : userRepo.findAll()) {
                    if (user.getEmail().equals(userRec.getEmail())) {
                        System.out.println("Username Already Exists Error");
                        return new ResponseEntity<String>("User Already Exists", HttpStatus.BAD_REQUEST);
                    }
                }
            }
            if (userRec.getPassword().length() < 8) {
                System.out.println("Weak Password Error");
                return new ResponseEntity<String>("Weak Password error!", HttpStatus.BAD_REQUEST);
            } else {
                System.out.println("User Insertion started!");
                userRepo.save(userRec);
                return new ResponseEntity<String>("User Added!", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage()+e.getStackTrace(),HttpStatus.BAD_REQUEST);
        }
    }
}
