package com.example.demo.repository;

import com.example.demo.model.AssignCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignCourseRepository extends JpaRepository<AssignCourse, Long> {
    // Custom query to fetch courses for a specific student
    List<AssignCourse> findByStudentId(Long studentId);
}
