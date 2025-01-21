package com.sms.school_management_system.Repositories;

import com.sms.school_management_system.Models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    // Custom queries can be defined here if needed
    // Example: find by subject name
    Subject findBySubjectName(String subjectName);
}
