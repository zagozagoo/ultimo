package com.trevis.startup.javaproject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.trevis.startup.javaproject.exception.AppResponseException;

/**
 * Catches the AppResponseException and sends an object with the message
 * to the client. Just use the ControllerAdvice annotation and throw 
 * AppResponseException out of trycatch scopes so this controller can detect it
 * @see AppResponseException
 * @see ControllerAdvice
 */
@ControllerAdvice
public class ExceptionHandlingController {
    

    @ExceptionHandler(value = AppResponseException.class)
    public ResponseEntity<Object> errorResponse(AppResponseException ex, WebRequest request) {

        return ResponseEntity.status(ex.getStatusCode()).body(new MessageRecord(ex.getMessage()));
    }


    private record MessageRecord(String message) {}
}
