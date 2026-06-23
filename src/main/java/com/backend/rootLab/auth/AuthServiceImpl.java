package com.backend.rootLab.auth;



import com.backend.rootLab.DTOS.Auth.AuthResponseDTO;
import com.backend.rootLab.DTOS.Auth.LoginRequestDTO;
import com.backend.rootLab.DTOS.Auth.RegisterRequestDTO;
import com.backend.rootLab.models.Role;
import com.backend.rootLab.models.UserModel;
import com.backend.rootLab.repository.UserRepository;
import com.backend.rootLab.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {

        UserModel user = UserModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.TEAM_LEAD)
                .enabled(true)
                .accountNonLocked(true)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserModel user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}