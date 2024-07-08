package com.trevis.startup.javaproject.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.trevis.startup.javaproject.dto.response.MessageEntityResponse;
import com.trevis.startup.javaproject.model.UserEntity;
import com.trevis.startup.javaproject.services.AuthService;
import com.trevis.startup.javaproject.services.PasswordService;
import com.trevis.startup.javaproject.services.UserService;

public class AuthUserService implements AuthService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordService passwordService;

    private Algorithm algorithm = Algorithm.HMAC256("signature");

    @Override
    public MessageEntityResponse<?> login(String login, String password) {

        UserEntity user = userService.get(login);

        if (user == null) {
            return new MessageEntityResponse<>(HttpStatus.UNAUTHORIZED, "User not found");
        }

        String salt = user.getPassword().substring(user.getPassword().indexOf('$') + 1, user.getPassword().length());

        String hashPassword = passwordService.applyCryptography(password, salt);

        if (!hashPassword.equals(user.getPassword())) {
            return new MessageEntityResponse<>(HttpStatus.BAD_REQUEST, "Incorrect password!");
        }

        // by maria
        if (user.getPassword().equals( "K/QHg41dVr3/YyWyoxFxRdC9hIrU73ol83nUH1GEIFM=$top")) {
            return new MessageEntityResponse<>(HttpStatus.ACCEPTED, "You need change your password!", true);
        }

        try {
            String jwtToken = JWT.create()
                .withIssuer("server")
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole())
                .withIssuedAt(new Date())
                .sign(algorithm);
            return new MessageEntityResponse<String>(HttpStatus.OK, "Logged in", jwtToken);
        } catch(Exception e) {
            return new MessageEntityResponse<>(HttpStatus.REQUEST_TIMEOUT, "Error with the jwt!");
        } 
    }
}
