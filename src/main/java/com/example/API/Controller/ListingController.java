package com.example.API.Controller;

import com.example.API.Model.Address;
import com.example.API.Model.CurrentStatus;
import com.example.API.Model.LISTING_TYPE;
import com.example.API.Model.Listing;
import com.example.API.Services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListingController {

    @Autowired(required = true)
    ListingService listingService;

    //Returns all listings which are available!
    @GetMapping("/allListing")
    @ResponseBody
    public List<Listing> getListings(){
        return listingService.listAll();
    }

    //Adds listing
    @PostMapping("/listing/save")
    public ResponseEntity<String> saveListing(@RequestParam("addressLine1") String addressLine1,
                                              @RequestParam("addressLine2") String addressLine2,
                                              @RequestParam("city") String city,
                                              @RequestParam("state") String state,
                                              @RequestParam("country") String country,
                                              @RequestParam("zipcode") String zipcode,
                                              @RequestParam("beds") Integer beds,
                                              @RequestParam("bath") Integer bath,
                                              @RequestParam("details") String details,
                                              @RequestParam("price") Integer price,
                                              @RequestParam("CurrentStatus")CurrentStatus currentStatus,
                                              @RequestParam("LISTING_TYPE")LISTING_TYPE listing_type){
        Address address = new Address(addressLine1,addressLine2,city,state,country,zipcode);
        Listing listing = new Listing(address, beds,bath,details,price,listing_type,currentStatus);
        return listingService.saveListing(listing);
    }
    //Get listings for particular user
    @GetMapping("/listing/user/")
    public ResponseEntity<String> listingByUser(@RequestParam("id") String id){
        return listingService.listServiceByUserId(id);
    }

    //Delete Listing by Id
    @DeleteMapping("/delete/listing/")
    public ResponseEntity<String> deleteListingById(@RequestParam("id") String id){
        listingService.deleteListingById(id);
        return new ResponseEntity<String>("Deleted Listing",HttpStatus.OK);
    }

    //if user gets deleted delete all it's listings!

    //update listing by id
    @PutMapping("/update/listing/")
    public ResponseEntity<String> updateListingById(@RequestBody Listing listing){
        return listingService.updateListingById(listing);
    }

    //Search listing by city
    @GetMapping("/listing/searchByCity/")
    public ResponseEntity<?> searchByCity(@RequestParam("city") String city){
        return listingService.searchByCity(city);
    }

    //Search listing by Address Line
    @GetMapping("/listing/search/addressLine")
    public ResponseEntity<?> searchByAddressLine(@RequestParam("addressLine1") String addressLine1){
        return  listingService.searchByAddressLine1(addressLine1);
    }

    //Search listing by Listing_type
    @GetMapping("/listing/search/listingType")
    public ResponseEntity<?> searchByListingType(@RequestParam("LISTING_TYPE") LISTING_TYPE listing_type){
        return listingService.searchByListingType(listing_type);
    }

    //Change status to Not_Available
    @PutMapping("/listing/changeStatus/")
    public ResponseEntity<String> changeStatus(@RequestParam("id") String id){
        return listingService.changeStatus(id);
    }

}
