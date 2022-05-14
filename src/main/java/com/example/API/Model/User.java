package com.example.API.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    @NotBlank(message = "FirstName is Required")
    private String fName;
    private String lName;
    @NotBlank(message = "Email is Required")
    @Email
    private String email;
    @Valid
    private Role role;
    @NotBlank(message = "Phone Number required")
    @Size(min=10,max=10,message = "Valid Phone Number required")
    private String phone;
    @NotBlank(message = "Password is required")
    @Size(min=8,message = "Atleast size of 8 is required")
    private String password;


    public User(String fName, String lName, String email, Role role, String password, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.role = role;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                ", FName='" + fName + '\'' +
                ", LName='" + lName + '\'' +
                ", Email='" + email + '\'' +
                ", role=" + role +
                ", Phone=" + phone +
                '}';
    }
}
