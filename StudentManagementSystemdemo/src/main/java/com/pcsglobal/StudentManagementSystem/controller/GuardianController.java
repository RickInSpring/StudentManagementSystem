package com.pcsglobal.StudentManagementSystem.controller;

import com.pcsglobal.StudentManagementSystem.model.Guardian;
import com.pcsglobal.StudentManagementSystem.model.Student;
import com.pcsglobal.StudentManagementSystem.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guardians")
public class GuardianController {

    @Autowired
    private GuardianService guardianService;

    // CREATE - Add a new guardian
    @PostMapping
    public ResponseEntity<Guardian> createGuardian(@RequestBody Guardian guardian) {
        Guardian newGuardian = guardianService.saveGuardian(guardian);
        return new ResponseEntity<>(newGuardian, HttpStatus.CREATED);
    }

    // READ - Get all guardians
    @GetMapping
    public ResponseEntity<List<Guardian>> getAllGuardians() {
        List<Guardian> guardians = guardianService.getAllGuardians();
        return new ResponseEntity<>(guardians, HttpStatus.OK);
    }

    // READ - Get guardian by ID
    @GetMapping("/{id}")
    public ResponseEntity<Guardian> getGuardianById(@PathVariable Long id) {
        return guardianService.getGuardianById(id)
                .map(guardian -> new ResponseEntity<>(guardian, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE - Update a guardian
    @PutMapping("/{id}")
    public ResponseEntity<Guardian> updateGuardian(@PathVariable Long id,
                                                   @RequestBody Guardian guardianDetails) {
        return guardianService.getGuardianById(id)
                .map(existingGuardian -> {
                    existingGuardian.setName(guardianDetails.getName());
                    existingGuardian.setPhone(guardianDetails.getPhone());
                    existingGuardian.setRelationship(guardianDetails.getRelationship());

                    Guardian updatedGuardian = guardianService.saveGuardian(existingGuardian);
                    return new ResponseEntity<>(updatedGuardian, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE - Delete a guardian
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuardian(@PathVariable Long id) {
        return guardianService.getGuardianById(id)
                .map(guardian -> {
                    guardianService.deleteGuardian(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Additional endpoints for managing the relationship with students

    // GET - Get all students for a guardian
    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getGuardianStudents(@PathVariable Long id) {
        return guardianService.getGuardianById(id)
                .map(guardian -> new ResponseEntity<>(guardian.getStudents(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
