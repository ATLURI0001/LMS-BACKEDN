package com.example.demo.model;

public class DashboardOverview {

    private long totalStudents;
    private long totalInstructors;
    private long totalCourses;

    public DashboardOverview(long totalStudents, long totalInstructors, long totalCourses) {
        this.totalStudents = totalStudents;
        this.totalInstructors = totalInstructors;
        this.totalCourses = totalCourses;
    }

    // Getters and Setters
    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalInstructors() {
        return totalInstructors;
    }

    public void setTotalInstructors(long totalInstructors) {
        this.totalInstructors = totalInstructors;
    }

    public long getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(long totalCourses) {
        this.totalCourses = totalCourses;
    }
}
