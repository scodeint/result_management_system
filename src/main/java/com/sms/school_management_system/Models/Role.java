package com.sms.school_management_system.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for Role entity

    @Enumerated(EnumType.STRING) // Persist the role as a String in the database
    @Column(nullable = false, unique = true) // Ensure unique roles
    private RoleType role;

    public enum RoleType {
        ADMIN,
        TEACHER,
        STUDENT
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
