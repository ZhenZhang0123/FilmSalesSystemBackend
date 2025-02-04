package com.zhenzhang0123.filmsalessystembackend.service;

import com.zhenzhang0123.filmsalessystembackend.dto.AuthRequest;
import com.zhenzhang0123.filmsalessystembackend.dto.AuthResponse;
import com.zhenzhang0123.filmsalessystembackend.dto.RegisterRequest;
import com.zhenzhang0123.filmsalessystembackend.model.Role;
import com.zhenzhang0123.filmsalessystembackend.model.User;
import com.zhenzhang0123.filmsalessystembackend.repository.UserRepository;
import com.zhenzhang0123.filmsalessystembackend.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        // Create new user
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(Set.of(Role.ROLE_USER))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(savedUser);

        return buildAuthResponse(savedUser, token);
    }

    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!user.isEnabled()) {
                throw new RuntimeException("Account is disabled");
            }
            if (!user.isAccountNonLocked()) {
                throw new RuntimeException("Account is locked");
            }
            String token = jwtUtil.generateToken(user);
            return buildAuthResponse(user, token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }

    private AuthResponse buildAuthResponse(User user, String token) {
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getAuthorities().stream()
                        .map(Role::name)
                        .collect(Collectors.toSet()))
                .build();
    }
}
