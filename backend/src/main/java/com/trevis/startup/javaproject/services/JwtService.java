package com.trevis.startup.javaproject.services;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtService {
    public Long jwtGetId(String token);
    public String jwtGetUsername(String token);
    public Integer jwtGetRole(String token);

    public DecodedJWT jwtIsValid(String token);
}
