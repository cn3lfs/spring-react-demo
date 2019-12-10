package com.chenwen.demo.student;

import java.util.UUID;

public class Course {
    private final UUID courseId;
    private final String name;
    private final String description;
    private final String department;
    private final String teacherName;

    public Course(UUID courseId, String name, String description, String department, String teacherName) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.department = department;
        this.teacherName = teacherName;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartment() {
        return department;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
