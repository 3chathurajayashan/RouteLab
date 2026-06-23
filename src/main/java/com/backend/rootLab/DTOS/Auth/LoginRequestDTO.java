package com.backend.rootLab.DTOS.Auth;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String email;

    private String password;
}