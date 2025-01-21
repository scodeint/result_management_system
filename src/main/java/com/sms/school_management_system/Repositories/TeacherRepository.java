package com.sms.school_management_system.Repositories;

import com.sms.school_management_system.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
