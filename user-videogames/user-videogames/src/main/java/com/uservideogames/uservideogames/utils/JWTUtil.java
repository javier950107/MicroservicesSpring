package com.uservideogames.uservideogames.utils;

import java.security.Key;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
    // call constants variables
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttl}")
    private long ttlMillis;

    public String createToken(String id, String user){
        // The jwt signature
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long currentMillis = System.currentTimeMillis();
        Date now = new Date(currentMillis);

        // sign jwt token
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Set jwt
        JwtBuilder builder = Jwts.builder()
            .setId(id)
            .setIssuedAt(now)
            .setSubject(user)
            .setIssuer(issuer)
            .signWith(signatureAlgorithm, signingKey);

        if(ttlMillis >= 0 ){
            long expMillis = currentMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Return the token after to build
        return builder.compact();
    }
}
