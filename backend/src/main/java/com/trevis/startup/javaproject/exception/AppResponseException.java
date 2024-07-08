package com.trevis.startup.javaproject.exception;

import com.trevis.startup.javaproject.controllers.ExceptionHandlingController;

/**
 * An exception class designed to provided treated answers to the client.
 * <p>
 * Along with the ExceptionHandlingController, it sends a object with a single prop
 * to the client called message with the value of the message used in the constructor.
 * @param message the message to be sent to the client who sent the request
 * @param status the status code of the response sent to the client
 * @see ExceptionHandlingController
 */
public class AppResponseException extends RuntimeException {
    
    private String message;
    private Integer statusCode;


    public AppResponseException(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public AppResponseException(String message) {
        this.message = message;
        this.statusCode = 400;
    }


    public Integer getStatusCode() {
        return statusCode;
    }
    public String getMessage() {
        return message;
    }
}
