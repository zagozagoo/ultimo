package com.trevis.startup.javaproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.javaproject.dto.payload.UserCreateEntityPayload;
import com.trevis.startup.javaproject.dto.payload.UserEntityPayload;
import com.trevis.startup.javaproject.dto.response.MessageEntityResponse;
import com.trevis.startup.javaproject.dto.response.MessagesEntityResponse;
import com.trevis.startup.javaproject.model.UserEntity;
import com.trevis.startup.javaproject.services.JwtService;
import com.trevis.startup.javaproject.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    JwtService jwtService;

    @PostMapping("/user")
    public MessagesEntityResponse create(@RequestBody UserCreateEntityPayload param,  @RequestHeader("Authorization") String token) {

        List<MessageEntityResponse<?>> messages = new ArrayList<>();

        if (jwtService.jwtIsValid(token) == null) {
            messages.add(new MessageEntityResponse<>(HttpStatus.UNAUTHORIZED, "User not found"));
            return new MessagesEntityResponse(messages);
        }

        if (jwtService.jwtGetRole(token) != 1) {
            messages.add(new MessageEntityResponse<>(HttpStatus.FORBIDDEN, "User not authorized"));
            return new MessagesEntityResponse(messages);
        }

        UserEntity user = service.create(new UserEntityPayload(param.login(), param.role()), param.department());

        if (user == null) {
            messages.add(new MessageEntityResponse<>(HttpStatus.BAD_REQUEST, "Username already in use!"));
        } else {
            messages.add(new MessageEntityResponse<UserEntity>(HttpStatus.OK, "User created successfully!", user));
        }

        return new MessagesEntityResponse(messages);
    }

    @PatchMapping("/user/{id}")
    public MessagesEntityResponse update(@RequestBody String password, @PathVariable Long id, @RequestHeader("Authorization") String token) {
        List<MessageEntityResponse<?>> messages = new ArrayList<>();

        if (jwtService.jwtIsValid(token) == null) {
            messages.add(new MessageEntityResponse<>(HttpStatus.UNAUTHORIZED, "User not found"));
            return new MessagesEntityResponse(messages);
        }

        if (service.updatePassword(password, id).messages().get(0).getValue() == null) {
            return service.updatePassword(password, id);
        }

        var user = service.updatePassword(password, id).messages().get(0).getValue();

        messages.add(new MessageEntityResponse<>(HttpStatus.OK, "Password updated successfully!", user));

        return new MessagesEntityResponse(messages);
    }
}
