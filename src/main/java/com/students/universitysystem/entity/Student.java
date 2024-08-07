package com.students.universitysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uniqueNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date dateOfBirth;
    private String studentGroup;

}