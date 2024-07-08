package com.trevis.startup.javaproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.javaproject.model.DepartmentEntity;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<DepartmentEntity, Long> {}
