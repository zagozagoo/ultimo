package com.trevis.startup.javaproject.services;

import com.trevis.startup.javaproject.dto.response.MessagesEntityResponse;

public interface PasswordService {
    String applyCryptography(String password, String salt);
    Boolean verifyCryptography(String password, String encryptedPassword);
    MessagesEntityResponse verifyRules(String password);
    String saltHash();
}
