package com.trevis.startup.javaproject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.javaproject.dto.payload.UserEntityPayload;
import com.trevis.startup.javaproject.model.UserEntity;
import com.trevis.startup.javaproject.services.AuthService;
import com.trevis.startup.javaproject.services.UserService;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;
    
    // @Test
    void testLogin() {
        UserEntity entity = userService.create(new UserEntityPayload("test", "123"), 0L);
        assertNotNull(authService.login(entity.getUsername(), "123"));
    } 
}
