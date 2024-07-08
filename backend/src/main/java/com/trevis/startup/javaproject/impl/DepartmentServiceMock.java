package com.trevis.startup.javaproject.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.trevis.startup.javaproject.model.DepartmentEntity;
import com.trevis.startup.javaproject.services.DepartmentService;

public class DepartmentServiceMock implements DepartmentService {

    private List<DepartmentEntity> departmentEntities = new ArrayList<>(
		Arrays.asList(new DepartmentEntity("BDO"), new DepartmentEntity("ICO"), new DepartmentEntity("Engenharia"))
	);

	@Override
	public List<DepartmentEntity> getAll() {
		return departmentEntities;
	}

	@Override
	public DepartmentEntity get(Long id) {
		for (DepartmentEntity departmentEntity : departmentEntities) {
			if (departmentEntity.getId().equals(id)) {
				return departmentEntity;
			}
		}
		return null;
	}
}
