package com.backend.rootLab.auth;

import com.backend.rootLab.DTOS.Auth.AuthResponseDTO;
import com.backend.rootLab.DTOS.Auth.LoginRequestDTO;
import com.backend.rootLab.DTOS.Auth.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO register(
            @RequestBody RegisterRequestDTO request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody LoginRequestDTO request
    ) {

        return authService.login(request);
    }
}