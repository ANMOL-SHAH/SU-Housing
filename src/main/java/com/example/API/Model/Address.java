package com.example.API.Model;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class Address {
    @NotBlank(message = "Address Line Cannot be Empty")
    private String addressLine1;
    private String addressLine2;
    @NotBlank(message = "City cannot be Empty")
    private String city;
    @NotBlank(message = "State Cannot be Empty")
    private String state;
    @NotBlank(message = "Country cannot be Empty")
    private String country;
    @NotBlank(message = "Zipcode cannot be Empty")
    private String zipCode;

    public Address(String addressLine1, String addressLine2, String city, String state, String country, String zipCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

}
