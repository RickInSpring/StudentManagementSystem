package com.pcsglobal.StudentManagementSystem.repo;

import com.pcsglobal.StudentManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
