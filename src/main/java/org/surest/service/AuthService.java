package org.surest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.surest.dto.AuthRequest;
import org.surest.dto.AuthResponse;
import org.surest.entity.Role;
import org.surest.entity.User;
import org.surest.repository.UserRepository;
import org.surest.security.JwtUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + authRequest.getUsername())
                );

        List<String> roleNames = user.getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        String jwtToken = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(
                jwtToken,
                user.getUsername(),
                roleNames
        );
    }
}
