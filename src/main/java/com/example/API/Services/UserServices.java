package com.example.API.Services;

import com.example.API.Model.Role;
import com.example.API.Model.User;
import com.example.API.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServices implements UserServiceInterface{

    @Autowired
    private UserRepo userRepo;


    public String getTest(){
        return "Test";
    }

    @Override
    public List<User> listAll() {
        List<User> products = new ArrayList<>();
        userRepo.findAll().forEach(products::add);
        System.out.print(products.size());
        return products;
    }

    @Override
    public ResponseEntity<String> getUserDetails(String id){
        try {
            List<User> user = userRepo.findAll();
            for (User u : user) {
                String idToString = u.getId().toString();
                if (id.equals(idToString)) {
                    return new ResponseEntity<String>(u.toString(), HttpStatus.OK);
                }
            }
            return new ResponseEntity<String>("No User Found",HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            return new ResponseEntity<String>("Error!",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteUser(String email){

        List<User> user = userRepo.findAll();
        for(User u:user){
            String emailToString = u.getEmail();
            if(emailToString.equals(email)){
                System.out.println(u.toString());
                userRepo.delete(u);
                //userRepo.deleteById(u.getId().toString());
                return new ResponseEntity<String>("User Deleted Successfully!",HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("User not Found",HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<String> deleteAllUser(){
        try {
            userRepo.deleteAll();
            return new ResponseEntity<String>("User Deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.toString(),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateUser(User user){
        Optional<User> u = userRepo.findById(user.getId());
        if(u.isPresent()){
            User retreivedUser = u.get();
            if(user.getFName()!=null){
                retreivedUser.setFName(user.getFName());
            } if(user.getLName()!=null){
                retreivedUser.setLName(user.getLName());
            } if(user.getEmail()!=null){
                retreivedUser.setEmail(user.getEmail());
            } if(user.getPassword()!=null){
                retreivedUser.setPassword(user.getPassword());
            } if(user.getPhone() != null){
                retreivedUser.setPhone(user.getPhone());
            } if(user.getRole() != null) {
                retreivedUser.setRole(user.getRole());
            }
            userRepo.save(retreivedUser);
            return new ResponseEntity<String>(retreivedUser.toString(),HttpStatus.OK);
        }
        return new ResponseEntity<>("User Updated",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getUserByRole(Role role) {
        System.out.println(role);
        List<User> user = userRepo.findByRole(role);
        if(user.size()!=0){
            List<User> userList = new ArrayList<>();
            user.forEach(userList::add);
            return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Avail!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> getRoleByEmail(String email){
        try {
            User user = userRepo.findRoleByEmail(email);
            return new ResponseEntity<String>(user.getRole().toString(),HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
