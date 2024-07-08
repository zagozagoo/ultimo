package com.trevis.startup.javaproject.services;

import java.util.List;

import com.trevis.startup.javaproject.dto.payload.ServiceEntityPayload;
import com.trevis.startup.javaproject.model.ServiceEntity;

public interface ServiceService {
    ServiceEntity create(ServiceEntityPayload payload, Long managerId);
    ServiceEntity get(Long id);
    List<ServiceEntity> get(String query, Integer pageIndex, Integer pageSize);
    ServiceEntity update(ServiceEntityPayload payload, Long id);
    void delete(Long id);
}
