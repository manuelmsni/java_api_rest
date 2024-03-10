/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.util;


import com.mycompany.api.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 *
 * @author manuelmsni
 */
public class JWTManager {
    
    public static String generateToken(User user) {
        Date expiration = new Date(System.currentTimeMillis() + Constants.SESSION_VALIDITY);
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId())) // Encode the user ID as the "subject" of the token
                .setExpiration(expiration)
                .signWith(getSecret(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean verifyToken(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret()).build().parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static Claims decodeToken(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(getSecret()).build().parseClaimsJws(jwtToken).getBody();
    }
    
    private static Key getSecret(){
        // TODO : Implement a secure method to get the secret
        return Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    }
}