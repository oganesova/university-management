package com.students.universitysystem.controller;

import com.students.universitysystem.entity.Student;
import com.students.universitysystem.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        log.info("Fetching all students");
        List<Student> students = studentService.findAll();
        log.info("Found {} students", students.size());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        log.info("Adding new student with uniqueNumber: {}", student.getUniqueNumber());
        studentService.save(student);
        log.info("Student added successfully");
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uniqueNumber}")
    public ResponseEntity<Void> deleteStudentByUniqueNumber(@PathVariable String uniqueNumber) {
        log.info("Attempting to delete student with uniqueNumber: {}", uniqueNumber);
        Student student = studentService.findByUniqueNumber(uniqueNumber);
        if (student != null) {
            studentService.deleteByUniqueNumber(uniqueNumber);
            log.info("Student with uniqueNumber {} deleted successfully", uniqueNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.warn("Student with uniqueNumber {} not found", uniqueNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}