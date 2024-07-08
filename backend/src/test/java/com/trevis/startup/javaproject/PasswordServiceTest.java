package com.trevis.startup.javaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.javaproject.services.PasswordService;

@SpringBootTest
public class PasswordServiceTest {
    
    @Autowired
    PasswordService passwordService;

    // @Test
    void testCryptography() {

        String testPassword = "123456";
        String wrongPassword = "12345";

        String cryptographed = passwordService.applyCryptography(testPassword);

        assertTrue(passwordService.verifyCryptography(testPassword, cryptographed));
        assertFalse(passwordService.verifyCryptography(wrongPassword, cryptographed));
    }

    // @Test
    void testVeirifyRules() {
        assertEquals(1, passwordService.verifyRules("@&%$"));
        assertEquals(2, passwordService.verifyRules("1234567"));
        assertEquals(3, passwordService.verifyRules("12345678"));
        assertEquals(4, passwordService.verifyRules("1234567A"));
        assertEquals(5, passwordService.verifyRules("12345678Am"));
    }
}
