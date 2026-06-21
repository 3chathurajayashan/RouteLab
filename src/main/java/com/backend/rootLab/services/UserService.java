package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.UserCreateDTO;
import com.backend.rootLab.DTOS.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO userCreateDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(String id);
    void deleteUser(String id);


}
