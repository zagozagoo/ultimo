package com.trevis.startup.javaproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.javaproject.model.ServiceEntity;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByNameContaining(String query);
}
