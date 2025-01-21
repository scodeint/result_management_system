package com.sms.school_management_system.Controllers;

import com.sms.school_management_system.Models.User;
import com.sms.school_management_system.Models.Role;
import com.sms.school_management_system.Services.UserService;
import com.sms.school_management_system.Repositories.RoleRepository; // Ensure this is imported
import com.sms.school_management_system.Repositories.UserRepository; // Ensure this is imported
import com.sms.school_management_system.Config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository; // Inject RoleRepository
    private final UserRepository userRepository; // Inject UserRepository
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService,
                          RoleRepository roleRepository,
                          UserRepository userRepository,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userService.isUsernameOrEmailTaken(user.getUsername(), user.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        return userService.authenticateUser(username, password)
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getUsername(), user.getRole().getRole().name());
                    return ResponseEntity.ok().body("Bearer " + token);
                })
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable Role.RoleType role) {
        List<User> users = userService.findUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin-data")
    public ResponseEntity<String> getAdminData(@RequestHeader("Authorization") String token) {
        String role = jwtUtil.getRoleFromToken(token);
        if ("ADMIN".equals(role)) {
            return ResponseEntity.ok("This is admin-only data.");
        }
        return ResponseEntity.status(403).body("Access denied.");
    }

    @GetMapping("/role-details/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        try {
            Role.RoleType roleType = Role.RoleType.valueOf(role.toUpperCase());
            Role foundRole = roleRepository.findByRole(roleType)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + role));
            List<User> users = userRepository.findByRole(foundRole);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid role type: " + role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}
