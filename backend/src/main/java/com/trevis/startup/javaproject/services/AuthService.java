package com.trevis.startup.javaproject.services;

import com.trevis.startup.javaproject.dto.response.MessageEntityResponse;

public interface AuthService {
    MessageEntityResponse<?> login(String login, String password);
}
