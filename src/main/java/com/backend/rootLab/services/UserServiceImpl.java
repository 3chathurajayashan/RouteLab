package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.UserCreateDTO;
import com.backend.rootLab.DTOS.UserResponseDTO;
import com.backend.rootLab.models.UserModel;
import com.backend.rootLab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO){
        UserModel userModel = UserModel.builder().firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .userName(userCreateDTO.getUserName())
                .email(userCreateDTO.getEmail())
                .password(userCreateDTO.getPassword())
                .jobTitle(userCreateDTO.getJobTitle())
                .bio(userCreateDTO.getBio())
                .role(userCreateDTO.getRole())
                .enabled(true)
                .accountNonLocked(true)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();

        return mapToResponse(
                userRepository.save(userModel)
        );
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(String id) {

        UserModel user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO mapToResponse(UserModel user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .jobTitle(user.getJobTitle())
                .bio(user.getBio())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .accountNonLocked(user.isAccountNonLocked())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
