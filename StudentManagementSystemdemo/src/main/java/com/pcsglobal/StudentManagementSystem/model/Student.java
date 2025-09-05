package com.pcsglobal.StudentManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
    private String firstname;
    private String surname;
    private String phone;
    private String gender;

    @ManyToOne
    private Guardian guardian;

    @OneToOne
    private Address address;

}