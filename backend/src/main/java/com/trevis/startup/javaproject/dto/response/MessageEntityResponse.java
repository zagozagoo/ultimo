package com.trevis.startup.javaproject.dto.response;

import org.springframework.http.HttpStatus;

public class MessageEntityResponse<T> {

    public HttpStatus status;
    public String message;
    public T value;

    public MessageEntityResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public MessageEntityResponse(HttpStatus status, String message, T value) {
        this.status = status;
        this.message = message;
        this.value = value;
    }

    public T getValue() {
        return this.value;
    } 
}


// return new MessageEntityResponse<UserEntity>(HttpStatus.CREATED, "User created", user)