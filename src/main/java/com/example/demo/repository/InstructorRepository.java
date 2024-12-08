package com.example.demo.repository;

import com.example.demo.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor findByEmailAndPassword(String email, String password);  // Custom query to find instructor by email and password
}
