package com.trevis.startup.javaproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.javaproject.dto.payload.AuthEntityPayload;
import com.trevis.startup.javaproject.dto.response.MessageEntityResponse;
import com.trevis.startup.javaproject.services.AuthService;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth")
    public MessageEntityResponse<?> login(@RequestBody AuthEntityPayload param) {
        return authService.login(param.login(), param.password()) ;
    }
}