package com.trevis.startup.javaproject.dto.response;

import com.trevis.startup.javaproject.model.DepartmentEntity;
import com.trevis.startup.javaproject.model.UserEntity;

public class UserEntityResponse {
    
    Long id;
    String userName;
    DepartmentEntity departmentEntity; 


    public UserEntityResponse(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.userName = userEntity.getUsername();
        this.departmentEntity = userEntity.getDepartmentEntity();
    }
}
