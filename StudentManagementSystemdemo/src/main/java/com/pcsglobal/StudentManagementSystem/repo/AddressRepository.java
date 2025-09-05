package com.pcsglobal.StudentManagementSystem.repo;

import com.pcsglobal.StudentManagementSystem.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
