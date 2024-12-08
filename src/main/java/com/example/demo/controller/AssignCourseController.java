package com.example.demo.controller;

import com.example.demo.model.AssignCourse;
import com.example.demo.repository.AssignCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // Adjust as per your frontend's URL
public class AssignCourseController {

    @Autowired
    private AssignCourseRepository assignCourseRepository;

    // Assign a new course to a student
    @PostMapping("/assign-course")
    public AssignCourse assignCourse(@RequestBody AssignCourse assignCourse) {
        System.out.println("Received payload: " + assignCourse); // Debugging
        return assignCourseRepository.save(assignCourse);
    }

    // Get all assigned courses
    @GetMapping("/assigned-courses")
    public List<AssignCourse> getAssignedCourses() {
        return assignCourseRepository.findAll(); // Fetch all assigned courses
    }

    // Get assigned courses by student ID
    @GetMapping("/assigned-courses/{studentId}")
    public List<AssignCourse> getAssignedCoursesByStudentId(@PathVariable Long studentId) {
        return assignCourseRepository.findByStudentId(studentId); // Fetch by student ID
    }

    // Edit an assigned course 
    

    // Delete an assigned course
    @DeleteMapping("/assigned-courses/{id}")
    public void deleteAssignedCourse(@PathVariable Long id) {
        Optional<AssignCourse> course = assignCourseRepository.findById(id);
        
        if (course.isPresent()) {
            assignCourseRepository.deleteById(id); // Delete the course by ID
        } else {
            throw new RuntimeException("Assigned course not found with id " + id);
        }
    }
}
