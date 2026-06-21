package com.backend.rootLab.controllers;




import com.backend.rootLab.DTOS.UserCreateDTO;
import com.backend.rootLab.DTOS.UserResponseDTO;
import com.backend.rootLab.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDTO createUser(
            @RequestBody UserCreateDTO dto) {

        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(
            @PathVariable String id) {

        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable String id) {

        userService.deleteUser(id);

        return "User Deleted Successfully";
    }
}
