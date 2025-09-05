package com.pcsglobal.StudentManagementSystem.serviceImpl;

import com.pcsglobal.StudentManagementSystem.model.Address;
import com.pcsglobal.StudentManagementSystem.model.Guardian;
import com.pcsglobal.StudentManagementSystem.model.Student;
import com.pcsglobal.StudentManagementSystem.repo.AddressRepository;
import com.pcsglobal.StudentManagementSystem.repo.GuardianRepository;
import com.pcsglobal.StudentManagementSystem.repo.StudentRepository;
import com.pcsglobal.StudentManagementSystem.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private GuardianRepository guardianRepository;


    @Override
    public Student saveStudent(Student student) {
        // Save or update address
        if (student.getAddress() != null) {
            Address address = student.getAddress();
            if (address.getAddressId() != null) {
                address = addressRepository.findById(address.getAddressId())
                        .orElseThrow(() -> new EntityNotFoundException("Address not found"));
            }
            student.setAddress(address);
        }

        // Save or update guardian
        if (student.getGuardian() != null) {
            Guardian guardian = student.getGuardian();
            if (guardian.getGuardianId() != null) {
                guardian = guardianRepository.findById(guardian.getGuardianId())
                        .orElseThrow(() -> new EntityNotFoundException("Guardian not found"));
            }
            student.setGuardian(guardian);
        }

        return studentRepository.save(student);
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
