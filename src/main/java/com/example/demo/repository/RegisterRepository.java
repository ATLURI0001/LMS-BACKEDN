package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RegisterUser;
import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterUser, Long> {

    Optional<RegisterUser> findByEmail(String email);  // Add this method
}
