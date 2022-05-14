package com.example.API.Model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Document(collection = "Listings")
public class Listing {
    @Id
    private String listingId;
    @Valid
    private Address address;
    @NotBlank(message = "Beds Cannot be Empty")
    @Min(1)
    @Max(8)
    private Integer beds;
    @Min(0)
    @Max(8)
    private Integer bath;
    private String details;
    private String listerID = "6260f33401cd3525dbd33043";
    // check listing type
    @Valid
    private LISTING_TYPE listing_type;
    private Date postedDate = new Date();
    @Min(400)
    @Max(2000)
    private Integer price;
    //Available or not
    @Valid
    private CurrentStatus currentStatus;

    public Listing(Address address, Integer beds, Integer bath, String details,Integer price, LISTING_TYPE listing_type, CurrentStatus currentStatus) {
        this.address = address;
        this.beds = beds;
        this.bath = bath;
        this.details = details;
        this.price = price;
        this.listing_type = listing_type;
        this.currentStatus = currentStatus;
    }



}
