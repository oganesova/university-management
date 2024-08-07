package com.students.universitysystem.repository;

import com.students.universitysystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByUniqueNumber(String uniqueNumber);
}
