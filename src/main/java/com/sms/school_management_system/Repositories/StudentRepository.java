package com.sms.school_management_system.Repositories;


import com.sms.school_management_system.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
