package com.chenwen.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/process")
    public List<ProductionProcess> getAllProcess() {
        return List.of(
                new ProductionProcess(0, "someProcess0", 0, 0, "someDevice1", 220, 0, 0, 0, 0, 0, 250, 30),
                new ProductionProcess(1, "someProcess1", 0, 0, "someDevice1", 220, 0, 0, 0, 0, 0, 250, 30),
                new ProductionProcess(2, "someProcess2", 0, 0, "someDevice1", 220, 0, 0, 0, 0, 0, 250, 30),
                new ProductionProcess(3, "someProcess3", 1, 1, "someDevice2", 200, 0, 0, 0, 0, 0, 300, 100),
                new ProductionProcess(4, "someProcess4", 1, 1, "someDevice2", 200, 0, 0, 0, 0, 0, 300, 100),
                new ProductionProcess(5, "someProcess5", 1, 1, "someDevice2", 200, 0, 0, 0, 0, 0, 300, 100),
                new ProductionProcess(6, "someProcess6", 1, 1, "someDevice2", 200, 0, 0, 0, 0, 0, 300, 100)
        );
    }

    @GetMapping(path = "/{studentId}/courses")
    public List<StudentCourse> getAllCoursesForStudent(@PathVariable UUID studentId) {
        return studentService.getAllCoursesForStudent(studentId);
    }

    @PostMapping
    public void addNewStudent(@RequestBody @Valid Student student) {
        studentService.addNewStudent(student);
    }

    @PostMapping(path = "/{studentId}/courses")
    public void addCourseForStudent(@PathVariable String studentId, @RequestBody StudentCourse studentCourse) {
        studentService.addCourseForStudent(studentCourse);
    }
}
