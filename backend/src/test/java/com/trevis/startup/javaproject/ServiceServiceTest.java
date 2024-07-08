package com.trevis.startup.javaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.javaproject.dto.payload.ServiceEntityPayload;
import com.trevis.startup.javaproject.exception.AppResponseException;
import com.trevis.startup.javaproject.services.ServiceService;

@SpringBootTest
public class ServiceServiceTest {
    
    @Autowired
    ServiceService serviceService;

    @Test
    void testServiceCreation() {

        String serviceName = "Service";
        String serviceDescription = "A generic service";
        Boolean serviceIntern = true;
        
        var service = serviceService.create(
            new ServiceEntityPayload(
                serviceName,
                serviceDescription,
                serviceIntern
            ), 1l, 1l
        );
        
        assertNotNull(service);
        assertEquals(serviceName, service.getName());
        assertEquals(serviceDescription, service.getDescription());
    }

    @Test
    void testServiceRead() {
        
        String serviceName = "Service";
        String serviceDescription = "A generic service";
        Boolean serviceIntern = true;
        
        var service = serviceService.create(
            new ServiceEntityPayload(
                serviceName,
                serviceDescription,
                serviceIntern
            ), 1l, 1l
        );
        var found = serviceService.get(service.getId());

        assertEquals(service.getName(), found.getName());
        assertThrows(AppResponseException.class, () -> { serviceService.get(10000l); });
    }

    @Test
    void testServiceUpdate() {

        String serviceName = "Service";
        String serviceDescription = "A generic service";
        Boolean serviceIntern = true;

        String updateServiceName = "Another Service";
        String updateServiceDescription = "A different service";
        Boolean updateServiceIntern = false;
        
        var service = serviceService.create(
            new ServiceEntityPayload(
                serviceName,
                serviceDescription,
                serviceIntern
            ), 1l, 1l
        );

        var updated = serviceService.update(
            new ServiceEntityPayload(
                updateServiceName, 
                updateServiceDescription, 
                updateServiceIntern
            ), service.getId()
        );

        assertNotNull(updated);
        assertEquals(updateServiceName, updated.getName());
        assertEquals(updateServiceDescription, updated.getDescription());
        assertEquals(updateServiceIntern, updated.getIntern());
    }

    @Test
    void testServiceDeletion() {

        String serviceName = "Service";
        String serviceDescription = "A generic service";
        Boolean serviceIntern = true;
        
        var service = serviceService.create(
            new ServiceEntityPayload(
                serviceName,
                serviceDescription,
                serviceIntern
            ), 1l, 1l
        );
        serviceService.delete(service.getId());

        assertThrows(AppResponseException.class, () -> { serviceService.get(service.getId()); });
    }
}
