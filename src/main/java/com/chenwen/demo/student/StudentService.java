package com.chenwen.demo.student;

import com.chenwen.demo.EmailValidator;
import com.chenwen.demo.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;
    private final EmailValidator emailValidator;

    @Autowired
    public StudentService(StudentDataAccessService studentDataAccessService, EmailValidator emailValidator) {
        this.studentDataAccessService = studentDataAccessService;
        this.emailValidator = emailValidator;
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
        // TODO Validate email
        if (!emailValidator.test(student.getEmail())) {
            throw new ApiRequestException("Email " + student.getEmail() + " not correct");
        }
        // TODO Check if email already exists
        if (studentDataAccessService.isEmailTaken(student.getEmail())) {
            throw new ApiRequestException("Email " + student.getEmail() + " is already exists");
        }
        studentDataAccessService.insertStudent(newStudentId, student);
    }
}
