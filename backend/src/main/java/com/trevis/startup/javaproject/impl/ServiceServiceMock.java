package com.trevis.startup.javaproject.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import com.trevis.startup.javaproject.dto.payload.ServiceEntityPayload;
import com.trevis.startup.javaproject.exception.AppResponseException;
import com.trevis.startup.javaproject.model.ServiceEntity;
import com.trevis.startup.javaproject.services.ServiceService;

public class ServiceServiceMock implements ServiceService {

    private List<ServiceEntity> servicesList = new ArrayList<>();
    private Long currentId = 1l;

	private Long getCurrentId() {
		return currentId++;
	}

    @Override
    public List<ServiceEntity> get(String query, Integer pageIndex, Integer pageSize) {

        if (pageIndex < 1) {
            pageIndex = 1;
        }

        return servicesList
            .stream()
            .filter(x -> x.getName().contains(query))
            .skip((pageIndex - 1) * pageSize)
            .limit(pageSize)
            .toList();
    }

    @Override
    public ServiceEntity create(ServiceEntityPayload payload, Long managerId) {
        if (payload.name().isEmpty()) throw new InvalidParameterException("Name");
        if (payload.description().isEmpty()) throw new InvalidParameterException("Description");

        ServiceEntity service = new ServiceEntity();
        service.setId(getCurrentId());
        service.setName(payload.name());
        service.setDescription(payload.description());
        service.setIntern(payload.intern());

        servicesList.add(service);

        return service;
    }

    @Override
    public ServiceEntity get(Long id) {
        try {
            return servicesList
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .get();
        } catch (Exception e) {
            throw new AppResponseException("Service not found", 404);
        }
    }

    @Override
    public ServiceEntity update(ServiceEntityPayload payload, Long id) {
        ServiceEntity entity = get(id);

        if (payload.name() != null) {
            entity.setName(payload.name());
        }

        if (payload.description() != null) {
            entity.setDescription(payload.description());
        }

        if (payload.intern() != null) {
            entity.setIntern(payload.intern());
        }

        return entity;
    }

    @Override
    public void delete(Long id) {
        ServiceEntity entity = get(id);

        servicesList.remove(entity);
    }
    
}
