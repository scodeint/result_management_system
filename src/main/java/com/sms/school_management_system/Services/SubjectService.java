package com.sms.school_management_system.Services;

import com.sms.school_management_system.Models.Subject;
import com.sms.school_management_system.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    // Get all subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // Get a subject by its ID
    public Optional<Subject> getSubjectById(int subjectId) {
        return subjectRepository.findById(subjectId);
    }

    // Get a subject by its name
    public Subject getSubjectByName(String subjectName) {
        return subjectRepository.findBySubjectName(subjectName);
    }

    // Save a new subject or update an existing subject
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Delete a subject by its ID
    public void deleteSubject(int subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    // Check if a subject exists by ID
    public boolean existsById(int subjectId) {
        return subjectRepository.existsById(subjectId);
    }
}
