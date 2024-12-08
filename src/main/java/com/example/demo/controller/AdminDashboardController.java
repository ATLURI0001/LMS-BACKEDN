package com.example.demo.controller;

import com.example.demo.repository.RegisterRepository;
import com.example.demo.repository.InstructorRepository;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AdminDashboardController {

    @Autowired
    private RegisterRepository registerRepository; // Manages students/registered users

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/students/count")
    public Long getStudentCount() {
        long count = registerRepository.count(); // counts the number of students
        return count;
    }

    @GetMapping("/instructors/count")
    public Long getInstructorCount() {
        long count = instructorRepository.count(); // counts the number of instructors
        return count;
    }

    @GetMapping("/courses/count")
    public Long getCourseCount() {
        long count = courseRepository.count(); // counts the number of courses
        return count;
    }

}
