package org.andarworld.authenticationservice.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.andarworld.authenticationservice.persistence.model.User;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    @Value("${security.secretKey}")
    private String secretKey;

    @Value("${security.expirationTime}")
    private long expirationTime;

    @Value("${security.issuer}")
    private String issuer;

    public String generateToken(User user) {
        return Jwts.builder()
                .issuer(issuer)
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public boolean validateToken(String token, User user) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject().equals(user.getEmail());
    }
}
