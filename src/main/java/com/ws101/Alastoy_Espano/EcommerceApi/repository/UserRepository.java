package com.ws101.Alastoy_Espano.EcommerceApi.repository;

import com.ws101.Alastoy_Espano.EcommerceApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * Finds a user by their username.
     * Spring Data JPA auto-implements this from the method name.
     */
    Optional<User> findByUsername(String username);
}
