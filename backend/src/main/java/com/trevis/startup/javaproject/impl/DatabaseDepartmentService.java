package com.trevis.startup.javaproject.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.javaproject.model.DepartmentEntity;
import com.trevis.startup.javaproject.repositories.DepartmentJpaRepository;
import com.trevis.startup.javaproject.services.DepartmentService;

public class DatabaseDepartmentService implements DepartmentService {

    @Autowired
    DepartmentJpaRepository repo;

    @Override
    public List<DepartmentEntity> getAll() {
        return repo.findAll();
    }

    @Override
    public DepartmentEntity get(Long id) {
        return repo.findById(id).get();
    }
}
