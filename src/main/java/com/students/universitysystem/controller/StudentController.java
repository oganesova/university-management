package com.students.universitysystem.controller;
import com.students.universitysystem.dto.StudentDto;
import com.students.universitysystem.service.StudentService;
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
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentDto addStudent(@RequestBody StudentDto studentDto) {
        return studentService.addStudent(studentDto);
    }

    @DeleteMapping("/{uniqueNumber}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String uniqueNumber) {
        studentService.deleteStudentByUniqueNumber(uniqueNumber);
        return ResponseEntity.ok().build();
    }
}
