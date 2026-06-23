package com.backend.rootLab.DTOS.Auth;

import com.backend.rootLab.models.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {

    private String token;

    private String email;

    private Role role;
}
