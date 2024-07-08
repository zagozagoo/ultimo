package com.trevis.startup.javaproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.trevis.startup.javaproject.impl.AuthUserService;
import com.trevis.startup.javaproject.impl.DatabaseDepartmentService;
import com.trevis.startup.javaproject.impl.DatabaseServiceService;
import com.trevis.startup.javaproject.impl.DatabaseUserService;
import com.trevis.startup.javaproject.impl.JwtAuthService;
import com.trevis.startup.javaproject.impl.PasswordAuthService;
import com.trevis.startup.javaproject.services.*;

@Configuration
public class DependenciesConfiguration {

    @Bean @Scope()
    AuthService authService() { 
        return new AuthUserService();
    }

    @Bean @Scope()
    DepartmentService departmentService() {
        return new DatabaseDepartmentService();
    }

    @Bean @Scope()
    PasswordService passwordService() {
        return new PasswordAuthService();
    }

    @Bean @Scope()
    ServiceService serviceService() {
        return new DatabaseServiceService();
    }

    @Bean @Scope()
    UserService userService() {
        return new DatabaseUserService();
    }

    @Bean @Scope()
    JwtService jwtService() {
        return new JwtAuthService();
    }

}