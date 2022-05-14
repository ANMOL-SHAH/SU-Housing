package com.example.API.Repo;

import com.example.API.Model.CurrentStatus;
import com.example.API.Model.LISTING_TYPE;
import com.example.API.Model.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ListingRepo extends MongoRepository<Listing,String> {
    @Query("{listerID :?0}")
    List<Listing> findByListerID (String listerID);

    @Query("{CurrentStatus:?0}")
    List<Listing> findAllByStatus(CurrentStatus currentStatus);

    @Query("{'Address.city':?0}")
    Optional<List<Listing>> findByCity(String city);

    @Query("{'Address.addressLine1' :?0}")
    Optional<List<Listing>> findByAddressLine(String addressLine1);

    @Query("{listing_type:?0}")
    Optional<List<Listing>> findByListingType(LISTING_TYPE listing_type);
}
