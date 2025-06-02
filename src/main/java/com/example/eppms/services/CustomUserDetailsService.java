package com.example.eppms.services;

import com.example.eppms.models.User;
import com.example.eppms.models.Userinrole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmailWithRoles(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getUserEmail(),
                user.getUserPassword(),
                true, // enabled
                true, // account non expired
                true, // credentials non expired
                true, // account non locked
                getAuthorities(user.getUserinroles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Userinrole> userInRoles) {
        return userInRoles.stream()
                .map(userInRole -> new SimpleGrantedAuthority("ROLE_" + userInRole.getRole().getRoleTitle()))
                .collect(Collectors.toList());
    }
} 