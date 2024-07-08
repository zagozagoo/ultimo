package com.trevis.startup.javaproject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.trevis.startup.javaproject.dto.payload.UserEntityPayload;
import com.trevis.startup.javaproject.dto.response.MessageEntityResponse;
import com.trevis.startup.javaproject.dto.response.MessagesEntityResponse;
import com.trevis.startup.javaproject.model.UserEntity;
import com.trevis.startup.javaproject.repositories.UserJpaRepository;
import com.trevis.startup.javaproject.services.DepartmentService;
import com.trevis.startup.javaproject.services.PasswordService;
import com.trevis.startup.javaproject.services.UserService;

public class DatabaseUserService implements UserService {

    @Autowired
    UserJpaRepository repo;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PasswordService passwordService;

    @Override
    public UserEntity create(UserEntityPayload payload, Long departmentId) {
        UserEntity user = new UserEntity();

        if (!repo.findByUsername(payload.userName()).isEmpty()) {
            return null;
        }

        user.setUsername(payload.userName());
        user.setRole(payload.role());
        user.setDepartmentEntity(departmentService.get(departmentId));
        user.setPassword("K/QHg41dVr3/YyWyoxFxRdC9hIrU73ol83nUH1GEIFM=$top"); // Teste123!banana

        repo.save(user);
        return user;
    }

    @Override
    public UserEntity get(String userName) {
        var query = repo.findByUsername(userName);
        if(query.isEmpty()) return null; 
        return query.get();
    }

    @Override
    public UserEntity get(Long id) {
        try {
            return repo.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        repo.delete(repo.findById(id).get());
    }

    @Override 
    public MessagesEntityResponse updatePassword(String password , Long id){

        List<MessageEntityResponse<?>> messages = new ArrayList<>();

        UserEntity user = repo.findById(id).get();

        if (user == null) {
            messages.add(new MessageEntityResponse<>(HttpStatus.UNAUTHORIZED, "User not found!"));
            return new MessagesEntityResponse(messages);
        }

        if (passwordService.verifyRules(password).messages().size() > 0) {
            return new MessagesEntityResponse(passwordService.verifyRules(password).messages());
        }

        String salt = passwordService.saltHash();

        String hashedPassword = passwordService.applyCryptography(password, salt); 

        user.setPassword(hashedPassword + "$" + salt);
        repo.save(user);

        messages.add(new MessageEntityResponse<UserEntity>(HttpStatus.OK, "Updated password successfully!", user));
        return new MessagesEntityResponse(messages);
        
    }
}
