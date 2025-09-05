package com.pcsglobal.StudentManagementSystem.controller;

import com.pcsglobal.StudentManagementSystem.model.Address;
import com.pcsglobal.StudentManagementSystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // CREATE - Add a new address
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address newAddress = addressService.saveAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }

    // READ - Get all addresses
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // READ - Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .map(address -> new ResponseEntity<>(address, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE - Update an address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id,
                                                 @RequestBody Address addressDetails) {
        return addressService.getAddressById(id)
                .map(existingAddress -> {
                    existingAddress.setStreet(addressDetails.getStreet());
                    existingAddress.setCity(addressDetails.getCity());
                    existingAddress.setState(addressDetails.getState());
                    existingAddress.setCountry(addressDetails.getCountry());
                    existingAddress.setPinCode(addressDetails.getPinCode());

                    Address updatedAddress = addressService.saveAddress(existingAddress);
                    return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE - Delete an address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .map(address -> {
                    addressService.deleteAddress(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
