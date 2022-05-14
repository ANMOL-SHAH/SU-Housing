package com.example.API.Test;

import com.example.API.ApiApplication;
import com.example.API.Model.Role;
import com.example.API.Model.User;
import com.example.API.Repo.UserRepo;
import com.example.API.Services.UserServices;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = ApiApplication.class)
public class UserServiceImpTest implements  UserServiceIntTest {

    @Mock
    private UserRepo userRepo;
    @Autowired(required = true)
    private UserServices userServices;

    User user;

    public UserServiceImpTest(){
        user = new User("ANMOL","Shah","ans@g.com", Role.ADMIN,"anmolshah","1234");
    }

    @Test
    @Override
    public void checkGetTest() {
        try{
        Mockito.when(userRepo.findById("6260f33401cd3525dbd33043")).thenReturn(Optional.ofNullable(user));
        Assert.assertEquals(user.toString(),userServices.getUserDetails("6260f33401cd3525dbd33043").getBody());
        System.out.println("Works");
        } catch(Exception e){
            System.out.println("Fails: "+e);
        }
    }
}
