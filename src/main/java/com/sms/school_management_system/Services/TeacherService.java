package com.sms.school_management_system.Services;

import com.sms.school_management_system.Models.Teacher;
import com.sms.school_management_system.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Save a new teacher
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Retrieve all teachers
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Find a teacher by ID
    public Optional<Teacher> findTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    // Update an existing teacher
    public Optional<Teacher> updateTeacher(Long id, Teacher updatedTeacher) {
        Optional<Teacher> existingTeacherOpt = teacherRepository.findById(id);
        if (existingTeacherOpt.isPresent()) {
            Teacher existingTeacher = existingTeacherOpt.get();
            existingTeacher.setFirstName(updatedTeacher.getFirstName());
            existingTeacher.setLastName(updatedTeacher.getLastName());
            existingTeacher.setEmail(updatedTeacher.getEmail());
            existingTeacher.setPhoneNumber(updatedTeacher.getPhoneNumber());
            return Optional.of(teacherRepository.save(existingTeacher));
        }
        return Optional.empty(); // Return empty Optional if teacher is not found
    }

    // Delete a teacher by ID
    public boolean deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
