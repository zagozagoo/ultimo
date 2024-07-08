package com.trevis.startup.javaproject.impl;

import com.trevis.startup.javaproject.dto.payload.UserEntityPayload;
import com.trevis.startup.javaproject.dto.response.MessageEntityResponse;
import com.trevis.startup.javaproject.dto.response.MessagesEntityResponse;
import com.trevis.startup.javaproject.exception.AppResponseException;
import com.trevis.startup.javaproject.model.UserEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;

import com.trevis.startup.javaproject.services.DepartmentService;
import com.trevis.startup.javaproject.services.PasswordService;
import com.trevis.startup.javaproject.services.UserService;

public class UserServiceMock implements UserService {

    @Autowired
    PasswordService passwordService;

    @Autowired
    DepartmentService departmentService;

    private List<UserEntity> userList = new ArrayList<>(
            Arrays.asList(new UserEntity("geraldo", "Senha@123", 1, departmentService.get(1l))));
    private Long currentId = 1l;

    private Long getCurrentId() {
        return currentId++;
    }

    @Override
    public UserEntity create(UserEntityPayload payload, Long departmentId) {
        UserEntity entity = new UserEntity();
        entity.setId(getCurrentId());
        entity.setUsername(payload.userName());
        userList.add(entity);

        return entity;
    }

    @Override
    public UserEntity get(String userName) {
        try {
            return userList
                    .stream()
                    .filter(x -> x.getUsername().equals(userName))
                    .findFirst()
                    .get();
        } catch (Exception e) {
            throw new AppResponseException("User not found", 404);
        }
    }

    @Override
    public UserEntity get(Long id) {
        try {
            return userList
                    .stream()
                    .filter(x -> x.getId().equals(id))
                    .findFirst()
                    .get();
        } catch (Exception e) {
            throw new AppResponseException("User not found", 404);
        }
    }

    @Override
    public void delete(Long id) {
        UserEntity entity = get(id);

        userList.remove(entity);
    }

    @Override
    public MessagesEntityResponse updatePassword(String password, Long id) {

        List<MessageEntityResponse<?>> messages = new ArrayList<>();

        UserEntity user = null;

        for (UserEntity userEntity : userList) {
            if (userEntity.getId() == id) {
                user = userEntity;
            }
        }

        if (user == null) {
            messages.add(new MessageEntityResponse<>(HttpStatus.UNAUTHORIZED, "User not found!"));
            return new MessagesEntityResponse(messages);
        }

        if (passwordService.verifyRules(password).messages().size() > 0) {
            return new MessagesEntityResponse(passwordService.verifyRules(password).messages());
        }

        String salt = passwordService.saltHash();

        String hashedPassword = passwordService.applyCryptography(password, salt);

        user.setPassword(hashedPassword);

        messages.add(new MessageEntityResponse<UserEntity>(HttpStatus.OK, "Updated password successfully!", user));
        return new MessagesEntityResponse(messages);
    }
}
