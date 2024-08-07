package com.students.universitysystem.repository;

import com.students.universitysystem.entity.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    Student findByUniqueNumber(String uniqueNumber);
    void save(Student student);
    void deleteByUniqueNumber(String uniqueNumber);
}