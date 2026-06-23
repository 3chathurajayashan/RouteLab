package com.backend.rootLab.auth;



import com.backend.rootLab.DTOS.Auth.AuthResponseDTO;
import com.backend.rootLab.DTOS.Auth.LoginRequestDTO;
import com.backend.rootLab.DTOS.Auth.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(
            RegisterRequestDTO request
    );

    AuthResponseDTO login(
            LoginRequestDTO request
    );
}
