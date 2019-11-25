package com.chenwen.demo.student;

import com.chenwen.demo.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;

    @Autowired
    public StudentService(StudentDataAccessService studentDataAccessService) {
        this.studentDataAccessService = studentDataAccessService;
    }

    List<Student> getAllStudents() {
//        throw new ApiRequestException("Oops cannot get all students");
        return studentDataAccessService.selectAllStudents();
    }

    void addNewStudent(Student student) {
        this.addNewStudent(null, student);
    }

    void addNewStudent(UUID studentId, Student student) {
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID());
        studentDataAccessService.insertStudent(newStudentId, student);
    }
}
