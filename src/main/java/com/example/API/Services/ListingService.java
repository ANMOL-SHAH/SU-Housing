package com.example.API.Services;

import com.example.API.Model.Address;
import com.example.API.Model.LISTING_TYPE;
import com.example.API.Model.Listing;
import com.example.API.Repo.ListingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.API.Model.CurrentStatus.AVAILABLE;
import static com.example.API.Model.CurrentStatus.NOT_AVAILABLE;

@Service
public class ListingService implements ListingServiceInterface{

    @Autowired
    private ListingRepo listingRepo;
    @Override
    public ResponseEntity<String> saveListing(@Valid  @RequestBody Listing listing) {
        try {
            listingRepo.save(listing);
            return new ResponseEntity<String>("Listing Added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Listing> listAll() {
        List<Listing> listing = new ArrayList<>();
        listingRepo.findAllByStatus(AVAILABLE).forEach(listing::add);
        System.out.println(listing.size());
        return listing;
    }

    @Override
    public ResponseEntity<String> listServiceByUserId(String id) {
        try {
            List<Listing> listings = listingRepo.findByListerID(id);
            return new ResponseEntity<String>(listings.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteListingById(String id) {
        try {
            Optional<Listing> listing = listingRepo.findById(id);
            if (listing.isPresent()) {
                Listing retrievedListing = listing.get();
                listingRepo.delete(retrievedListing);
                return new ResponseEntity<String>("Deleted Listing", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("No listing with this id found!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> updateListingById(Listing listing) {
        try {
            Optional<Listing> l = listingRepo.findById(listing.getListingId());
            if (l.isPresent()) {
                Listing retrievedListing = l.get();
                Address address = retrievedListing.getAddress();
                if (listing.getAddress().getAddressLine1() != null) {
                    address.setAddressLine1(listing.getAddress().getAddressLine1());
                }
                if (listing.getAddress().getAddressLine2() != null) {
                    address.setAddressLine2(listing.getAddress().getAddressLine2());
                }
                if (listing.getAddress().getCity() != null) {
                    address.setCity(listing.getAddress().getCity());
                }
                if (listing.getAddress().getAddressLine1() != null) {
                    address.setAddressLine1(listing.getAddress().getAddressLine1());
                }
                if (listing.getListing_type() != null) {
                    retrievedListing.setListing_type(listing.getListing_type());
                }
                if (listing.getBath() != null) {
                    retrievedListing.setBath(listing.getBath());
                }
                if (listing.getBeds() != null) {
                    retrievedListing.setBeds(listing.getBeds());
                }
                if (listing.getDetails() != null) {
                    retrievedListing.setDetails(listing.getDetails());
                }
                if (listing.getPrice() != null) {
                    retrievedListing.setPrice(listing.getPrice());
                }
                retrievedListing.setPostedDate(new Date());
                retrievedListing.setAddress(address);
                listingRepo.save(retrievedListing);
                return new ResponseEntity<String>("Updated Listing - " + listing.getListingId(), HttpStatus.OK);
            }
            return new ResponseEntity<String>("Listing Not Found" + listing.getListingId(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> changeStatus(String id) {
        try {
            Optional<Listing> listing = listingRepo.findById(id);
            if (listing.isPresent()) {
                Listing retrievedListing = listing.get();
                if (retrievedListing.getCurrentStatus().equals(AVAILABLE)) {
                    retrievedListing.setCurrentStatus(NOT_AVAILABLE);
                    listingRepo.save(retrievedListing);
                    return new ResponseEntity<String>("Status of property updated", HttpStatus.OK);
                } else {
                    retrievedListing.setCurrentStatus(AVAILABLE);
                    listingRepo.save(retrievedListing);
                    return new ResponseEntity<String>("Status of property updated", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<String>("Error?",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> searchByCity(String city) {
        try {
            Optional<List<Listing>> listing = listingRepo.findByCity(city);
            List<Listing> listings = new ArrayList<>();
            if(listing.isPresent()){
                List<Listing> list = listing.get();
                list.forEach(listings::add);
            }
            return new ResponseEntity<>(listings, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> searchByAddressLine1(String addressLine1) {
        try {
            Optional<List<Listing>> listing = listingRepo.findByAddressLine(addressLine1);
            List<Listing> listings = new ArrayList<>();
            if(listing.isPresent()){
                List<Listing> list = listing.get();
                list.forEach(listings::add);
            }
            return new ResponseEntity<>(listings, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> searchByListingType(LISTING_TYPE listing_type) {
        try {
            Optional<List<Listing>> listing = listingRepo.findByListingType(listing_type);
            System.out.println(listing_type);
            System.out.println(listing);
            List<Listing> listings = new ArrayList<>();
            if(listing.isPresent()){
                List<Listing> listingList = listing.get();
                listingList.forEach(listings::add);
            }
            return new ResponseEntity<>(listings, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
