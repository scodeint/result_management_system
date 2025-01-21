package com.sms.school_management_system.Controllers;

import com.sms.school_management_system.Models.Teacher;
import com.sms.school_management_system.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    // Create a new Teacher
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        try {
            // Set createdAt and updatedAt timestamps
            teacher.setCreatedAt(new Date());
            teacher.setUpdatedAt(new Date());

            // Ensure isActive is explicitly set (default to true if null)
            if (teacher.getActive() == null) {
                teacher.setActive(true);
            }

            Teacher savedTeacher = teacherRepository.save(teacher);
            logger.info("Teacher with ID {} created successfully", savedTeacher.getId());
            return ResponseEntity.status(201).body(savedTeacher); // Return 201 status
        } catch (Exception e) {
            logger.error("Error creating teacher: ", e);
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }

    // Get all Teachers
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return ResponseEntity.ok(teachers);
    }

    // Get a Teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Teacher
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
        try {
            Optional<Teacher> existingTeacher = teacherRepository.findById(id);
            if (existingTeacher.isPresent()) {
                Teacher teacher = existingTeacher.get();
                teacher.setFirstName(teacherDetails.getFirstName());
                teacher.setLastName(teacherDetails.getLastName());
                teacher.setDateOfBirth(teacherDetails.getDateOfBirth());
                teacher.setGender(teacherDetails.getGender());
                teacher.setAddress(teacherDetails.getAddress());
                teacher.setEmail(teacherDetails.getEmail());
                teacher.setPhoneNumber(teacherDetails.getPhoneNumber());
                teacher.setEmploymentId(teacherDetails.getEmploymentId());
                teacher.setQualification(teacherDetails.getQualification());
                teacher.setSpecialization(teacherDetails.getSpecialization());
                teacher.setYearsOfExperience(teacherDetails.getYearsOfExperience());
                teacher.setJoiningDate(teacherDetails.getJoiningDate());
                teacher.setSalary(teacherDetails.getSalary());
                teacher.setDepartment(teacherDetails.getDepartment());
                teacher.setClassesAssigned(teacherDetails.getClassesAssigned() != null ? teacherDetails.getClassesAssigned() : new ArrayList<>());
                teacher.setSubjectsTaught(teacherDetails.getSubjectsTaught() != null ? teacherDetails.getSubjectsTaught() : new ArrayList<>());
                teacher.setPerformanceRating(teacherDetails.getPerformanceRating());
                teacher.setFeedback(teacherDetails.getFeedback());
                teacher.setUpdatedAt(new Date());
                teacher.setActive(teacherDetails.getActive() != null ? teacherDetails.getActive() : teacher.getActive());


                Teacher updatedTeacher = teacherRepository.save(teacher);
                logger.info("Teacher with ID {} updated successfully", id);
                return ResponseEntity.ok(updatedTeacher);
            } else {
                logger.error("Teacher with ID {} not found", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error updating teacher: ", e);
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }

    // Delete a Teacher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        try {
            if (teacherRepository.existsById(id)) {
                teacherRepository.deleteById(id);
                logger.info("Teacher with ID {} deleted successfully", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.error("Teacher with ID {} not found", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error deleting teacher: ", e);
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }
}
