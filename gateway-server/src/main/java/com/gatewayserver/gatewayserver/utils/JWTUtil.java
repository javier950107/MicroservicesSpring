package com.gatewayserver.gatewayserver.utils;

import java.security.Key;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {
    // call constants variables
    @Value("${security.jwt.secret}")
    private String secret;

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean isTokenInvalid(String token){
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }
}
