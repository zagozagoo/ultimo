package com.trevis.startup.javaproject.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.trevis.startup.javaproject.services.JwtService;

public class JwtAuthService implements JwtService {
    
    private Algorithm algorithm = Algorithm.HMAC256("signature");

    @Override
    public DecodedJWT jwtIsValid(String token) {
        DecodedJWT decodedJwt;
        
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("server").build();
            decodedJwt = verifier.verify(token);

            return decodedJwt;

        } catch (JWTVerificationException e) {
            return null;
        }
    }

    @Override
    public Long jwtGetId(String token) {
        
        if (jwtIsValid(token) != null) {
            Claim id = jwtIsValid(token).getClaim("id");
            return id.asLong();
        }

        return null;
    }

    @Override
    public String jwtGetUsername(String token) {
        
        if (jwtIsValid(token) != null) {
            Claim username = jwtIsValid(token).getClaim("username");
            return username.asString();
        }

        return null;
    }

    @Override
    public Integer jwtGetRole(String token) {
        
        if (jwtIsValid(token) != null) {
            Claim role = jwtIsValid(token).getClaim("role");
            return role.asInt();
        }

        return null;
    }
}
