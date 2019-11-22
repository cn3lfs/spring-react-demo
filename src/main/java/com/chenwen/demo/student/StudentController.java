package com.chenwen.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("students")
public class StudentController {
    @GetMapping
    public List<Student> getAllStudents() {
        return List.of(
                new Student(UUID.randomUUID(), "chen", "wen", "chenwen@qq.com", Student.Gender.MALE),
                new Student(UUID.randomUUID(), "liu", "xiang", "liuxiang@qq.com", Student.Gender.MALE),
                new Student(UUID.randomUUID(), "li", "li", "lili@qq.com", Student.Gender.FEMALE)
        );
    }
}
