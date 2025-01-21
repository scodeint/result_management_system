package com.sms.school_management_system.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Access the role through the RoleType enum and call name() on it
        return List.of(() -> "ROLE_" + user.getRole().getRole().name());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify according to your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify according to your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify according to your logic
    }

    @Override
    public boolean isEnabled() {
        return user.isActive(); // Modify according to your logic
    }
}
