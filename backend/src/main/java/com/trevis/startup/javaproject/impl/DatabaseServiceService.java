package com.trevis.startup.javaproject.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.javaproject.dto.payload.ServiceEntityPayload;
import com.trevis.startup.javaproject.model.ServiceEntity;
import com.trevis.startup.javaproject.repositories.ServiceJpaRepository;
import com.trevis.startup.javaproject.services.ServiceService;
import com.trevis.startup.javaproject.services.UserService;

public class DatabaseServiceService implements ServiceService {
    
    @Autowired
    ServiceJpaRepository repo;

    @Autowired
    UserService userService;

    @Override
    public ServiceEntity get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public ServiceEntity create(ServiceEntityPayload payload, Long managerId) {
        ServiceEntity newService = new ServiceEntity();

        newService.setName(payload.name());
        newService.setDescription(payload.description());
        newService.setIntern(payload.intern());

        if (userService.get(managerId) == null || userService.get(managerId).getRole() != 2 ){
            return null;
        }

        newService.setManager(userService.get(managerId));

        repo.save(newService);
        return newService;
    }


    @Override
    public List<ServiceEntity> get(String query, Integer pageIndex, Integer pageSize) {
        if (pageIndex < 1) { // começa a contagem a partir do um
            pageIndex = 1;
        }

        return repo.findByNameContaining(query)
            .stream()
            .filter(x -> x.getName().contains(query))
            .skip((pageIndex - 1) * pageSize)
            .limit(pageSize)
            .toList();
    }

    @Override
    public ServiceEntity update(ServiceEntityPayload payload, Long id) {
        ServiceEntity service;
        
        service = repo.findById(id).get();
        
        if (service == null) {
           return null;
        }

        service.setName(payload.name());
        service.setDescription(payload.description());
        service.setIntern(payload.intern());

        repo.save(service);
        return service;
    }

    @Override
    public void delete(Long id) {
        repo.delete(repo.findById(id).get());
    }

}
