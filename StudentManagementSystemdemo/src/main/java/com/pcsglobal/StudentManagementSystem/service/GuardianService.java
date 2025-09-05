package com.pcsglobal.StudentManagementSystem.service;

import com.pcsglobal.StudentManagementSystem.model.Guardian;
import java.util.List;
import java.util.Optional;

public interface GuardianService {
    Guardian saveGuardian(Guardian guardian);
    List<Guardian> getAllGuardians();
    Optional<Guardian> getGuardianById(Long id);
    void deleteGuardian(Long id);
}
