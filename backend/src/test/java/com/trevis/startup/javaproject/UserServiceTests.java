package com.trevis.startup.javaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.javaproject.dto.payload.UserEntityPayload;
import com.trevis.startup.javaproject.exception.AppResponseException;
import com.trevis.startup.javaproject.model.UserEntity;
import com.trevis.startup.javaproject.services.UserService;

@SpringBootTest
public class UserServiceTests {
	@Autowired
	UserService userService;

    @Test
    void testCreate() {
        String username = "UserTester1";
        String password = "123321";
        UserEntity entity = userService.create(new UserEntityPayload(username, password), 0L);

        assertNotNull(entity);
        assertEquals(username, entity.getUsername());
        assertEquals(password, entity.getPassword());
    }

    @Test
    void testGetByUsername(){
        String username = "UserTester2";
        String password = "password";
        
        UserEntity entity = userService.create(new UserEntityPayload(username, password), 0L);
        UserEntity found = userService.get(username);

        assertEquals(entity.getId(), found.getId());
    }

    @Test
    void testGetById(){
        String username = "UserTester3";
        String password = "password";
        
        UserEntity entity = userService.create(new UserEntityPayload(username, password), 0L);
        UserEntity found = userService.get(entity.getId());

        assertEquals(entity.getId(), found.getId());
    }

    @Test
    void testUpdate() {
        String username = "UserTester4";
        String password = "password";
        String updateUsername = "UserTester5";
        String updatePassword = "newpassword";
        
        UserEntity entity = userService.create(new UserEntityPayload(username, password), 0L);
        userService.update(new UserEntityPayload(updateUsername, updatePassword), entity.getId());

        assertEquals(updateUsername, entity.getUsername());
    }

    @Test
    void testeDelete() {

        String username = "UserTester6";
        String password = "password";

        UserEntity entity = userService.create(new UserEntityPayload(username, password), 0L);
        userService.delete(entity.getId());

        assertThrows(AppResponseException.class, () -> { userService.get(entity.getId()); });
    }
    
}
