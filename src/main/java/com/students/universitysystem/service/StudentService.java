package com.students.universitysystem.service;
import com.students.universitysystem.entity.Student;
import com.students.universitysystem.repository.StudentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentService implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, (ResultSet rs) -> {
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = mapResultSetToStudent(rs);
                students.add(student);
            }
            return students;
        });
    }

    @Override
    public Student findByUniqueNumber(String uniqueNumber) {
        String sql = "SELECT * FROM students WHERE unique_number = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{uniqueNumber}, (rs, rowNum) -> mapResultSetToStudent(rs));
    }
    @Override
    public void deleteByUniqueNumber(String uniqueNumber) {
        String sql = "DELETE FROM students WHERE unique_number = ?";
        jdbcTemplate.update(sql, uniqueNumber);
    }
    @Override
    public void save(Student student) {
        String sql = "INSERT INTO students (unique_number, first_name, last_name, middle_name, date_of_birth, student_group) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, student.getUniqueNumber(), student.getFirstName(), student.getLastName(), student.getMiddleName(), student.getDateOfBirth(), student.getStudentGroup());
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

