package com.students.universitysystem.service;
import com.students.universitysystem.entity.Student;
import com.students.universitysystem.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class StudentService implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        log.info("Fetching all students");
        String sql = "SELECT * FROM students";
        List<Student> students = jdbcTemplate.query(sql, (ResultSet rs) -> {
            List<Student> studentList = new ArrayList<>();
            while (rs.next()) {
                Student student = mapResultSetToStudent(rs);
                studentList.add(student);
            }
            log.info("Found {} students", studentList.size());
            return studentList;
        });
        return students;
    }

    @Override
    public Student findByUniqueNumber(String uniqueNumber) {
        log.info("Fetching student with uniqueNumber: {}", uniqueNumber);
        String sql = "SELECT * FROM students WHERE unique_number = ?";
        Student student = jdbcTemplate.queryForObject(sql, new Object[]{uniqueNumber}, (rs, rowNum) -> mapResultSetToStudent(rs));
        if (student != null) {
            log.info("Found student with uniqueNumber: {}", uniqueNumber);
        } else {
            log.warn("No student found with uniqueNumber: {}", uniqueNumber);
        }
        return student;
    }

    @Override
    public void deleteByUniqueNumber(String uniqueNumber) {
        log.info("Attempting to delete student with uniqueNumber: {}", uniqueNumber);
        String sql = "DELETE FROM students WHERE unique_number = ?";
        int rowsAffected = jdbcTemplate.update(sql, uniqueNumber);
        if (rowsAffected > 0) {
            log.info("Student with uniqueNumber {} deleted successfully", uniqueNumber);
        } else {
            log.warn("No student found with uniqueNumber: {} to delete", uniqueNumber);
        }
    }

    @Override
    public void save(Student student) {
        log.info("Saving student with uniqueNumber: {}", student.getUniqueNumber());
        String sql = "INSERT INTO students (unique_number, first_name, last_name, middle_name, date_of_birth, student_group) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, student.getUniqueNumber(), student.getFirstName(), student.getLastName(), student.getMiddleName(), student.getDateOfBirth(), student.getStudentGroup());
        log.info("Student with uniqueNumber {} saved successfully", student.getUniqueNumber());
    }

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setUniqueNumber(rs.getString("unique_number"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setMiddleName(rs.getString("middle_name"));
        student.setDateOfBirth(rs.getDate("date_of_birth"));
        student.setStudentGroup(rs.getString("student_group"));
        return student;
    }
}

