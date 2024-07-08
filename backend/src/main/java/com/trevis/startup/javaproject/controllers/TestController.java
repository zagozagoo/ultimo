package com.trevis.startup.javaproject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.javaproject.exception.AppResponseException;

@RestController
public class TestController {

    @GetMapping("/vote")
    public ResponseEntity<String> test(@RequestParam Integer age) {

        if(age < 16) throw new AppResponseException("NÃO PODE VOTAR", 400);

        return ResponseEntity.ok("Voto no Eneas confirmado.");
    }   
}