package com.sms.school_management_system.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor // Generates a no-argument constructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;

    private String address;
    private String email;
    private String phoneNumber;

    private String employmentId;
    private String qualification;
    private String specialization;
    private int yearsOfExperience;

    private Date joiningDate;
    private double salary;
    private String department;

    @ElementCollection
    private List<String> classesAssigned;

    @ElementCollection
    private List<String> subjectsTaught;

    private double performanceRating;
    private String feedback;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Default constructor
    public Teacher() {
        // Required by JPA
    }

    // Parameterized constructor
    public Teacher(String firstName, String lastName, Date dateOfBirth, String gender, String address,
                   String email, String phoneNumber, String employmentId, String qualification,
                   String specialization, int yearsOfExperience, Date joiningDate, double salary,
                   String department, List<String> classesAssigned, List<String> subjectsTaught,
                   double performanceRating, String feedback, boolean isActive, Date createdAt, Date updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employmentId = employmentId;
        this.qualification = qualification;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.joiningDate = joiningDate;
        this.salary = salary;
        this.department = department;
        this.classesAssigned = classesAssigned != null ? classesAssigned : List.of(); // Prevent null pointer
        this.subjectsTaught = subjectsTaught != null ? subjectsTaught : List.of(); // Prevent null pointer
        this.performanceRating = performanceRating;
        this.feedback = feedback;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters (You can remove these if using Lombok @Data)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(String employmentId) {
        this.employmentId = employmentId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getClassesAssigned() {
        return classesAssigned;
    }

    public void setClassesAssigned(List<String> classesAssigned) {
        this.classesAssigned = classesAssigned != null ? classesAssigned : List.of(); // Prevent null pointer
    }

    public List<String> getSubjectsTaught() {
        return subjectsTaught;
    }

    public void setSubjectsTaught(List<String> subjectsTaught) {
        this.subjectsTaught = subjectsTaught != null ? subjectsTaught : List.of(); // Prevent null pointer
    }

    public double getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(double performanceRating) {
        this.performanceRating = performanceRating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isActive() {
        return isActive;
    }
    // Getters and Setters
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
