package com.example.API.Repo;

import com.example.API.Model.Role;
import com.example.API.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    @Query("{Role :?0}")
    List<User> findByRole(Role role);

    @Query("{email :?0}")
    User findRoleByEmail(String email);

    @Query("{email :?0}")
    Boolean existsByEmail(String email);
}
