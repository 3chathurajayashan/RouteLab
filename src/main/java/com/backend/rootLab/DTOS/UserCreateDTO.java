package com.backend.rootLab.DTOS;

import com.backend.rootLab.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String bio;
    private String jobTitle;
    private Role role;

}
