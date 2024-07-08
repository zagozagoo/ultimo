package com.trevis.startup.javaproject.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import com.trevis.startup.javaproject.dto.response.MessageEntityResponse;
import com.trevis.startup.javaproject.dto.response.MessagesEntityResponse;
import com.trevis.startup.javaproject.services.PasswordService;

public class PasswordAuthService implements PasswordService{
    
    @Override
    public String applyCryptography(String password, String salt) {
        String hashedPassword = password + salt;

        try {
            
            MessageDigest passDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = passDigest.digest(hashedPassword.getBytes(StandardCharsets.UTF_8));

            hashedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            return null;
        }

        return hashedPassword + "$" + salt;
    }

    @Override
    public Boolean verifyCryptography(String password, String encryptedPassword) {
        String pass = encryptedPassword.substring(0, encryptedPassword.indexOf('$'));
        String salt = encryptedPassword.substring(encryptedPassword.indexOf('$') + 1, encryptedPassword.length());

        String hashPassword = applyCryptography(password, salt);

        if (hashPassword.equals(pass)) {
            return true;
        }

        return false;
    }

    @Override
    public MessagesEntityResponse verifyRules(String password) {
       
        List<MessageEntityResponse<?>> messages = new ArrayList<>();

        Matcher regexLength = Pattern.compile(".{8,}").matcher(password);

        if (!regexLength.matches()) {
            messages.add(new MessageEntityResponse<>(HttpStatus.BAD_GATEWAY, "Less than 8 characters!"));
        }

        Matcher regexLowerCase = Pattern.compile("[a-z]").matcher(password);

        if (!regexLowerCase.matches()) {
            messages.add(new MessageEntityResponse<>(HttpStatus.BAD_GATEWAY, "No lower case characters"));
        }

        Matcher regexUpperCase = Pattern.compile("[A-Z]").matcher(password);
        
        if (!regexUpperCase.matches()) {
            messages.add(new MessageEntityResponse<>(HttpStatus.BAD_GATEWAY, "No upper case characters"));
        }

        Matcher regexNumber = Pattern.compile("\\d").matcher(password);
        
        if (!regexNumber.matches()) {
            messages.add(new MessageEntityResponse<>(HttpStatus.BAD_GATEWAY, "No numbers"));
        }

        return new MessagesEntityResponse(messages);
    }

     @Override
    public String saltHash() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        try {
            MessageDigest saltDigest = MessageDigest.getInstance("SHA-256");
            saltDigest.update(salt);
        } catch (Exception e) {
            return null;
        }

        String StringSalt = Base64.getEncoder().encodeToString(salt);

        return StringSalt;
    }
}
