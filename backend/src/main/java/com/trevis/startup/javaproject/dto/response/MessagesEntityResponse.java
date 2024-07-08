package com.trevis.startup.javaproject.dto.response;

import java.util.List;

public record MessagesEntityResponse(List<MessageEntityResponse<?>> messages) {}
