package com.betting.backend.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;//key is used to verify requests using its signature

    @Value("${jwt.expirationMs}")
    private long expirationMs;//controls how long a generated JWT token will remain valid before it is considered expired and the backend rejects it
    /**
     * in essance when a user logs in the generated token will last this long before having to log in again
     *
     *
     *
     */

    private Key getSignKey() {
        /**
         * The SignKey method is a key used to sign in the Json Web Token so that the server can prove the token was genereated by the backend
         * and it cannot be forged
         *
         * every Jwt has 3 parts
         *
         * Header: tells what algorithim was used
         * Payload: Contains the claims
         * Signature: generated using the secret key
         *
         *
         */
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        /**
         * The generateToken method generates a JWT that authenticates the user for future requests,
         * encodes key metadata and is cryptographically signed using the signkey
         *
         *
         *
         */
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
}

    private Claims extractAllClaims(String token) {
        /**
         * In essance claims are key value pairs inside a token that describe the user and token metadata
         * bascially it has all the important information for the token - when is it going to expire, user etc...
         *
         *
         */
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
