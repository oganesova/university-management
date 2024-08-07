package com.students.universitysystem.service;

import com.students.universitysystem.dto.StudentDto;
import com.students.universitysystem.entity.Student;
import com.students.universitysystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentDto::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public StudentDto addStudent(StudentDto studentDto) {
        Student student = StudentDto.mapDtoToEntity(studentDto);
        student = studentRepository.save(student);
        return StudentDto.mapEntityToDto(student);
    }

    public void deleteStudentByUniqueNumber(String uniqueNumber) {
        Student student = studentRepository.findByUniqueNumber(uniqueNumber);
        if (student != null) {
            studentRepository.delete(student);
        }
    }
}
