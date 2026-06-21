package com.backend.rootLab.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    private String profilePicture;
    private String jobTitle;
    private String bio;

     private Role role;
    private boolean enabled;
    private boolean accountNonLocked;

    private List<String> projectIds;

    private LocalDate createdAt;
    private LocalDate updatedAt;

}
