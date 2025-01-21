package com.sms.school_management_system.Repositories;

import com.sms.school_management_system.Models.Role;
import com.sms.school_management_system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    List<User> findByRole(Role role); // Add this method
}