package com.trevis.startup.javaproject.services;

import java.util.List;

import com.trevis.startup.javaproject.model.DepartmentEntity;

public interface DepartmentService {
    List<DepartmentEntity> getAll();
    DepartmentEntity get(Long id);
}
