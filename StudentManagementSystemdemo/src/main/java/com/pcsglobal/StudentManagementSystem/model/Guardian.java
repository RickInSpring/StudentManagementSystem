package com.pcsglobal.StudentManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String guardianId;
    private String name;
    private String phone;
    private String relationship;

    @OneToMany(mappedBy = "guardian")
    private List<Student> students;
}
