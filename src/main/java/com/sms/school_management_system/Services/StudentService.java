package com.sms.school_management_system.Services;

import com.sms.school_management_system.Models.Student;
import com.sms.school_management_system.Models.SubjectScore;
import com.sms.school_management_system.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Method to get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Method to add a new student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Method to update an existing student
    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    // Method to delete a student by ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Method to add a SubjectScore to a student
    @Transactional
    public Student addSubjectScore(Long studentId, SubjectScore subjectScore) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        student.addSubjectScore(subjectScore);  // Use the method in the Student class to manage the relationship
        return studentRepository.save(student); // Save the updated student with the new SubjectScore
    }

    // Method to remove a SubjectScore from a student
    @Transactional
    public Student removeSubjectScore(Long studentId, Long subjectScoreId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        SubjectScore subjectScore = student.getSubjectScores().stream()
                .filter(ss -> ss.getId().equals(subjectScoreId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("SubjectScore not found"));

        student.removeSubjectScore(subjectScore);  // Use the method in the Student class to remove the subject score
        return studentRepository.save(student);    // Save the updated student with the subject score removed
    }
}
