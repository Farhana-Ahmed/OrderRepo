package com.project.retail.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil
{
    private String secretKey = "secret";

    public String generateToken(String username){
        return Jwts.builder().setSubject(username).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }


}
