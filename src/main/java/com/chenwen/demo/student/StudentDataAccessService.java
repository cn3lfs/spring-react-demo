package com.chenwen.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StudentDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Student> selectAllStudents() {
        String sql = "SELECT student_id, first_name, last_name, email, gender FROM student";
        return jdbcTemplate.query(sql, mapStudentFromDb());
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdStr);

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            String genderStr = resultSet.getString("gender").toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderStr);
            return new Student(studentId, firstName, lastName, email, gender);
        };
    }

    void insertStudent(UUID newStudentId, Student student) {
        String sql = "INSERT INTO student (student_id, first_name, last_name, email, gender) Values(?, ?, ?, ?, ?::gender)";
        jdbcTemplate.update(sql, newStudentId, student.getFirstName(), student.getLastName(), student.getEmail(), student.getGender().name().toUpperCase());
    }

    @SuppressWarnings("ConstantConditions")
    boolean isEmailTaken(String email) {
        String sql = "SELECT EXISTS (SELECT 1 FROM student WHERE email = ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, (resultSet, i) -> resultSet.getBoolean(1));
    }

    public List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
        String sql = " SELECT student_id, course_id, name, description, department, teacher_name, start_date, end_date, grade FROM student " +
                "JOIN student_course USING (student_id)" +
                "JOIN course USING (course_id) " +
                "WHERE student.student_id = ?";
        return jdbcTemplate.query(sql, new Object[] {studentId},mapStudentCourseFromDb());
    }

    private RowMapper<StudentCourse> mapStudentCourseFromDb() {
        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdStr);

            String courseIdStr = resultSet.getString("course_id");
            UUID courseId = UUID.fromString(courseIdStr);

            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String department = resultSet.getString("department");
            String teacherName = resultSet.getString("teacher_name");
            LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
            LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
            Integer grade = Optional.ofNullable(resultSet.getInt("grade")).orElse(null);
            return new StudentCourse(studentId, courseId, name, description, department, teacherName, startDate, endDate, grade);
        };
    }

    void insertStudentCourse(StudentCourse studentCourse) {
        String sql = "INSERT INTO student_course (student_id, course_id, start_date, end_date, grade) Values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, studentCourse.getStudentId(), studentCourse.getCourseId(), studentCourse.getStartDate(), studentCourse.getEndDate(), studentCourse.getGrade());
    }
}
