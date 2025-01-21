package com.sms.school_management_system.Services;

import com.sms.school_management_system.Models.Role;
import com.sms.school_management_system.Models.User;
import com.sms.school_management_system.Repositories.RoleRepository;
import com.sms.school_management_system.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUsernameOrEmailTaken(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    public User saveUser(User user) {
        Role role = roleRepository.findByRole(user.getRole().getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + user.getRole().getRole()));
        user.setRole(role); // Ensure role is managed
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            Role role = roleRepository.findByRole(updatedUser.getRole().getRole())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + updatedUser.getRole().getRole()));
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Rehash password
            existingUser.setRole(role); // Ensure managed role
            existingUser.setActive(updatedUser.isActive());
            return userRepository.save(existingUser);
        });
    }

    public List<User> findUsersByRole(Role.RoleType roleType) {
        Role role = roleRepository.findByRole(roleType)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleType));
        return userRepository.findByRole(role);
    }
}
