package com.devmind.greatreads.service;

import com.devmind.greatreads.model.User;
import com.devmind.greatreads.security.UserDetailsImplementation;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JWTService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.accessTokenValidityMS}")
    private int jwtExpirationMs;

    @Value("${jwt.cookieName}")
    private String jwtCookie;

    public ResponseCookie generateJwtCookie(UserDetailsImplementation userPrincipal) {
        String jwt = generateTokenForUser(userPrincipal.getUser());
        return ResponseCookie.from(jwtCookie, jwt).maxAge(60 * 60).httpOnly(true).build();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            log.error("Something went wrong: " + e.getMessage());
        }
        return false;
    }

    public String generateTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
