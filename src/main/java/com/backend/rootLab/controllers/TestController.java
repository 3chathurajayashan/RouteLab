package com.backend.rootLab.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @GetMapping("api/test/me")
    public String getCurrentUser(Authentication authentication){
        return authentication.getName();
    }

}
