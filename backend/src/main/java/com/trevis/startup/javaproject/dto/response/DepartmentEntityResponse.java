package com.trevis.startup.javaproject.dto.response;

import java.util.List;

import com.trevis.startup.javaproject.model.DepartmentEntity;

public record DepartmentEntityResponse(String message, List<DepartmentEntity> data ) {}
