package com.students.universitysystem.dto;

import com.students.universitysystem.entity.Student;
import lombok.Data;

import java.util.Date;

@Data
public class StudentDto {
    private Long id;
    private String uniqueNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date dateOfBirth;
    private String studentGroup;

    public static StudentDto mapEntityToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setUniqueNumber(student.getUniqueNumber());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setMiddleName(student.getMiddleName());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setStudentGroup(student.getStudentGroup());
        return dto;
    }

    public static Student mapDtoToEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setUniqueNumber(dto.getUniqueNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setMiddleName(dto.getMiddleName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setStudentGroup(dto.getStudentGroup());
        return student;
    }
}
