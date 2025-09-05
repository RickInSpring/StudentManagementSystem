package com.pcsglobal.StudentManagementSystem.service;

import com.pcsglobal.StudentManagementSystem.model.Address;
import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address saveAddress(Address address);
    List<Address> getAllAddresses();
    Optional<Address> getAddressById(Long id);
    void deleteAddress(Long id);
}
