package com.example.demo.controller;

import com.example.demo.model.Instructor;
import com.example.demo.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private InstructorRepository instructorRepository;

   
    @GetMapping
    public List<Instructor> getAllInstructors() {
        logger.info("Fetching all instructors");
        return instructorRepository.findAll();
    }

    // Add a new instructor - default role is "INSTRUCTOR"
    @PostMapping
    public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor instructor) {
        try {
            // Set the role to "INSTRUCTOR" by default
            instructor.setRole("INSTRUCTOR");

            Instructor savedInstructor = instructorRepository.save(instructor);
            logger.info("Instructor added: {}", savedInstructor);
            return new ResponseEntity<>(savedInstructor, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding instructor: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an instructor
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        if (instructorRepository.existsById(id)) {
            instructor.setId(id);
            Instructor updatedInstructor = instructorRepository.save(instructor);
            logger.info("Instructor updated: {}", updatedInstructor);
            return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
        } else {
            logger.warn("Instructor not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }	

    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInstructor(@PathVariable Long id) {
        try {
            if (instructorRepository.existsById(id)) {
                instructorRepository.deleteById(id);
                logger.info("Instructor deleted with id: {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Instructor not found with id: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting instructor: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
