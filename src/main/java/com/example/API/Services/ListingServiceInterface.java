package com.example.API.Services;

import com.example.API.Model.LISTING_TYPE;
import com.example.API.Model.Listing;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ListingServiceInterface {
    ResponseEntity<String> saveListing(Listing listing);
    List<Listing> listAll();
    ResponseEntity<String> listServiceByUserId(String id);
     ResponseEntity<String> changeStatus(String id);
    ResponseEntity<String> deleteListingById(String id);
    ResponseEntity<String> updateListingById(Listing listing);
    ResponseEntity<?> searchByCity(String city);
    ResponseEntity<?> searchByAddressLine1(String addressLine1);
    ResponseEntity<?> searchByListingType(LISTING_TYPE listing_type);
}
