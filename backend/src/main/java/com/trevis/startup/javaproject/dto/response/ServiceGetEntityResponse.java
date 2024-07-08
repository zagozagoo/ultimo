package com.trevis.startup.javaproject.dto.response;

import java.util.List;

import com.trevis.startup.javaproject.model.ServiceEntity;

public record ServiceGetEntityResponse(MessageEntityResponse<?> message, List<ServiceEntity> services) {}
