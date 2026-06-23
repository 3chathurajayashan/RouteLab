package com.backend.rootLab.DTOS.Auth;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String password;
}