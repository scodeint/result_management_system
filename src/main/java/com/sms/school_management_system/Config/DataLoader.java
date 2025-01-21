package com.sms.school_management_system.Config;

import com.sms.school_management_system.Models.Role;
import com.sms.school_management_system.Models.User;
import com.sms.school_management_system.Repositories.RoleRepository;
import com.sms.school_management_system.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // Ensure roles are saved to the database first
        Role adminRole = roleRepository.findByRole(Role.RoleType.ADMIN).orElseGet(() -> {
            Role role = new Role();
            role.setRole(Role.RoleType.ADMIN);
            return roleRepository.save(role);
        });

        Role teacherRole = roleRepository.findByRole(Role.RoleType.TEACHER).orElseGet(() -> {
            Role role = new Role();
            role.setRole(Role.RoleType.TEACHER);
            return roleRepository.save(role);
        });

        Role studentRole = roleRepository.findByRole(Role.RoleType.STUDENT).orElseGet(() -> {
            Role role = new Role();
            role.setRole(Role.RoleType.STUDENT);
            return roleRepository.save(role);
        });

        // Create users and assign existing roles
        if (userRepository.findByUsername("adminuser").isEmpty()) {
            User adminUser = new User(
                    "Admin",
                    "User",
                    adminRole,
                    "adminuser",
                    "admin@example.com",
                    passwordEncoder.encode("adminpassword"),
                    true
            );
            userRepository.save(adminUser);
        }

        if (userRepository.findByUsername("teacheruser").isEmpty()) {
            User teacherUser = new User(
                    "Teacher",
                    "User",
                    teacherRole,
                    "teacheruser",
                    "teacher@example.com",
                    passwordEncoder.encode("teacherpassword"),
                    true
            );
            userRepository.save(teacherUser);
        }

        if (userRepository.findByUsername("studentuser").isEmpty()) {
            User studentUser = new User(
                    "Student",
                    "User",
                    studentRole,
                    "studentuser",
                    "student@example.com",
                    passwordEncoder.encode("studentpassword"),
                    true
            );
            userRepository.save(studentUser);
        }
    }
}
