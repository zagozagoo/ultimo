package com.trevis.startup.javaproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.javaproject.dto.response.DepartmentEntityResponse;
import com.trevis.startup.javaproject.services.DepartmentService;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService service;

    @GetMapping("/department")
    public DepartmentEntityResponse get() {
        return new DepartmentEntityResponse("Returning all departments", service.getAll());
    }
}
