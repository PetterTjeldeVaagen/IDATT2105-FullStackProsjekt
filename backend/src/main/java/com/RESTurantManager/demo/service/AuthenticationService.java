package com.RESTurantManager.demo.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Service class for handling authentication-related operations, such as generating and validating JWT tokens.
 */
@Service
public class AuthenticationService {
    private final SecretKey secretKey;
    private final long expirationTime;

    /**
     * Constructor for AuthenticationService. Initializes the secret key and expiration time for JWT tokens based on application properties.
     * @param secret the secret key used for signing JWT tokens
     * @param expiration the expiration time for JWT tokens in milliseconds
     */
    public AuthenticationService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationTime = expiration;
    }

    /**
     * Generates a JWT token for the given email. The token contains the email as the subject and is signed with the secret key.
     * @param email the email for which to generate the JWT token
     * @return the generated JWT token
     */
    public String getJWTToken(String email) {
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey)
                .compact();
        return token;
    }

    /**
     * Validates the given JWT token. Checks if the token is well-formed, not expired, and signed with the correct secret key.
     * @param token the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Extracts the email from the given JWT token. Parses the token and retrieves the subject, which contains the email.
     * @param token the JWT token from which to extract the email
     * @return the email extracted from the token
     */
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();

    }
}
