package com.example.demo.service;

import com.example.demo.repository.RegisterRepository;
import com.example.demo.repository.InstructorRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.model.DashboardOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService {

    @Autowired
    private RegisterRepository registerRepository; // Manages students/registered users

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;

    public DashboardOverview getDashboardOverview() {
        // Assuming RegisterUser represents students, instructors can be part of Instructor table
        long totalStudents = registerRepository.count(); // Count students (RegisterUser)
        long totalInstructors = instructorRepository.count(); // Count instructors
        long totalCourses = courseRepository.count(); // Count courses

        return new DashboardOverview(totalStudents, totalInstructors, totalCourses);
    }
}
