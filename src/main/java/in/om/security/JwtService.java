package in.om.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	

    private final SecretKey secretKey =
            Keys.hmacShaKeyFor(
                "my-super-secret-key-for-expense-tracker-2026"
                    .getBytes()
            );

    private final long expirationTime = 1000 * 60 * 60; // 1 hour

    // Generate JWT
    public String generateToken(CustomUserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(
                    new Date(System.currentTimeMillis()
                            + expirationTime)
                )
                .signWith(secretKey)
                .compact();
    }

    // Extract email from JWT
    public String extractUsername(String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    // Extract all claims
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Validate JWT
    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    // Check expiration
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
}