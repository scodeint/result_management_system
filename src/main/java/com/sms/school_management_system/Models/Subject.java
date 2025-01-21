package com.sms.school_management_system.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId; // Unique identifier for the subject

    private String subjectName; // Name of the subject
    private String subjectCode; // Short code or abbreviation for the subject
    private String department; // Department or faculty of the subject
    private int teacherId; // ID of the teacher assigned to teach the subject
    private String semester; // The semester or academic period
    private String academicYear; // Academic year in which the subject is taught
    private int credits; // The number of credits the subject is worth
    private String status; // Active, discontinued, or suspended
    private LocalDate createdAt; // Date and time the subject was created
    private LocalDate updatedAt; // Date and time the subject entry was last updated

    // No-argument constructor required by JPA
    public Subject() {}

    // Constructor with parameters
    public Subject(String subjectName, String subjectCode, String department, int teacherId,
                   String semester, String academicYear, int credits, String status, LocalDate createdAt, LocalDate updatedAt) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.department = department;
        this.teacherId = teacherId;
        this.semester = semester;
        this.academicYear = academicYear;
        this.credits = credits;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", department='" + department + '\'' +
                ", teacherId=" + teacherId +
                ", semester='" + semester + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", credits=" + credits +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
