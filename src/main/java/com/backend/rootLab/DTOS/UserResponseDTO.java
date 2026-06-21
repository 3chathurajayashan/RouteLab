package com.backend.rootLab.DTOS;

import com.backend.rootLab.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private String id;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;


    private String profilePicture;
    private String jobTitle;
    private String bio;

  private Role role;

    private boolean enabled;
    private boolean accountNonLocked;



    private LocalDate createdAt;
    private LocalDate updatedAt;
}
