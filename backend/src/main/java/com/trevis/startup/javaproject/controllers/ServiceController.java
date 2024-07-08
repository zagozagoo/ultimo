package com.trevis.startup.javaproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.javaproject.dto.payload.ServiceEntityPayload;
import com.trevis.startup.javaproject.dto.response.MessageEntityResponse;
import com.trevis.startup.javaproject.dto.response.ServiceCreateEntityResponse;
import com.trevis.startup.javaproject.dto.response.ServiceGetEntityResponse;
import com.trevis.startup.javaproject.model.ServiceEntity;
import com.trevis.startup.javaproject.services.JwtService;
import com.trevis.startup.javaproject.services.ServiceService;
import com.trevis.startup.javaproject.services.UserService;

@RestController
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;
    
    @GetMapping("/service")
    public ServiceGetEntityResponse get(@RequestParam String query, @RequestParam int page, @RequestParam int size, @RequestHeader("Authorization") String token) {
        
        if (jwtService.jwtIsValid(token) == null) {
            return new ServiceGetEntityResponse(
                new MessageEntityResponse<>(HttpStatus.UNAUTHORIZED, "User not found!"),
                null
            );
        }
        
        return new ServiceGetEntityResponse(
            new MessageEntityResponse<>(HttpStatus.OK, "Returning page!")
            ,serviceService.get(query,page + 1, size)
        );
        // somando um no page, pois o metodo get criado usa a pagina a partir do index 1
    }

    @PostMapping("/service")
    public MessageEntityResponse<?> create(@RequestBody ServiceCreateEntityResponse param, @RequestHeader("Authorization") String token) {
   
        if (jwtService.jwtIsValid(token) == null) {
            return new MessageEntityResponse<>(HttpStatus.UNAUTHORIZED, "User not found!");
        }

        ServiceEntity newService = serviceService.create(new ServiceEntityPayload(param.name(), param.description(), param.intern()), jwtService.jwtGetId(token));

        if (newService == null) {
            return new MessageEntityResponse<>(HttpStatus.FORBIDDEN, "User not authorized!");
        }

        return new MessageEntityResponse<ServiceEntity>(HttpStatus.CREATED, "Service created successfully!", newService);           
    }

    @PutMapping("/service/{id}")
    public MessageEntityResponse<?> update(@PathVariable Long id, @RequestBody ServiceEntityPayload param, @RequestHeader("Authorization") String token) {
        
        if (jwtService.jwtIsValid(token) == null) {
            return new MessageEntityResponse<>(HttpStatus.UNAUTHORIZED, "User not found!");
        }

        ServiceEntity newService = serviceService.update(param, id);
        if (newService == null) {
            return new MessageEntityResponse<>(HttpStatus.BAD_REQUEST, "Service not found!");
        }
        return new MessageEntityResponse<ServiceEntity>(HttpStatus.OK, "Service updated successfully!", newService);
    }

    @DeleteMapping("/service/{id}")
    public MessageEntityResponse<?> delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        
        ServiceEntity newService = serviceService.get(id);
        
        if (newService == null) {
            return new MessageEntityResponse<>(HttpStatus.BAD_REQUEST, "Service not found!");
        }
        
        serviceService.delete(id);
        return new MessageEntityResponse<>(HttpStatus.OK, "Deleted successfully!");
    }
}
