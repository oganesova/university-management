package com.students.universitysystem.controller;

import com.students.universitysystem.entity.Student;
import com.students.universitysystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uniqueNumber}")
    public ResponseEntity<Void> deleteStudentByUniqueNumber(@PathVariable String uniqueNumber) {
        Student student = studentService.findByUniqueNumber(uniqueNumber);
        if (student != null) {
            studentService.deleteByUniqueNumber(uniqueNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}