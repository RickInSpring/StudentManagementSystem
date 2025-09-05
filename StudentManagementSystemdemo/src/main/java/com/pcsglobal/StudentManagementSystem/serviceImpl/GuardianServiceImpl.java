package com.pcsglobal.StudentManagementSystem.serviceImpl;

import com.pcsglobal.StudentManagementSystem.model.Guardian;
import com.pcsglobal.StudentManagementSystem.repo.GuardianRepository;
import com.pcsglobal.StudentManagementSystem.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuardianServiceImpl implements GuardianService {

    @Autowired
    private GuardianRepository guardianRepository;

    @Override
    public Guardian saveGuardian(Guardian guardian) {
        return guardianRepository.save(guardian);
    }

    @Override
    public List<Guardian> getAllGuardians() {
        return guardianRepository.findAll();
    }

    @Override
    public Optional<Guardian> getGuardianById(Long id) {
        return guardianRepository.findById(id);
    }

    @Override
    public void deleteGuardian(Long id) {
        guardianRepository.deleteById(id);
    }
}
