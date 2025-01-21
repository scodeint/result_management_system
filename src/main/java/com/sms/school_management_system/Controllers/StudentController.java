package com.sms.school_management_system.Controllers;

import com.sms.school_management_system.Models.Student;
import com.sms.school_management_system.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Only users with the ROLE_ADMIN can add a student
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok("Student added successfully!");
    }

    // Both ADMIN and TEACHER roles can view all students
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    // Both ADMIN AND TEACHER can Get a student by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(404).body("Student not found!");
        }
    }

    // Only ADMIN can Update a student's details
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudentOpt = studentRepository.findById(id);
        if (existingStudentOpt.isPresent()) {
            Student existingStudent = existingStudentOpt.get();
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setAdmissionNumber(updatedStudent.getAdmissionNumber());
            existingStudent.setStudentClass(updatedStudent.getStudentClass());
            existingStudent.setSection(updatedStudent.getSection());
            existingStudent.setAddress(updatedStudent.getAddress());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
            existingStudent.setGuardianName(updatedStudent.getGuardianName());
            existingStudent.setGuardianPhoneNumber(updatedStudent.getGuardianPhoneNumber());
            existingStudent.setGuardianEmail(updatedStudent.getGuardianEmail());
            existingStudent.setRelationship(updatedStudent.getRelationship());
            existingStudent.setTerm(updatedStudent.getTerm());
            existingStudent.setAcademicYear(updatedStudent.getAcademicYear());
            existingStudent.setAdmissionDate(updatedStudent.getAdmissionDate());
            existingStudent.setActive(updatedStudent.isActive());
            studentRepository.save(existingStudent);
            return ResponseEntity.ok("Student updated successfully!");
        } else {
            return ResponseEntity.status(404).body("Student not found!");
        }
    }

    // Only ADMIN role can delete students
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Student not found!");
        }
    }
}
