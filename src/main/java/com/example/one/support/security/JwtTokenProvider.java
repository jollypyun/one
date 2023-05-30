package com.example.one.support.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.validTime}")
    private long validTime;
    private Key key;
    private static final String AUTH = "Bearer ";

    public String createAccessToken(Authentication authentication) {
        log.info("Auth: " + authentication);
        long date = new Date().getTime();
        Date expire = new Date(date + 5400 * 1000);
        String jwt = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(date))
                .claim("auth", authentication.getAuthorities())
                .signWith(signingKey(secret), SignatureAlgorithm.HS256)
                .setExpiration(expire)
                .compact();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, AUTH + jwt);
        return jwt;
    }

    public boolean validateToken(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            log.info("유효한 토큰이 없습니다. : " + e.getMessage());
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        if (claims.get("auth") == null) {
            throw new RuntimeException("This is invalid token");
        }
        String id = claims.getSubject();
        List<SimpleGrantedAuthority> list= Stream.of(claims.get("auth").toString()).map(SimpleGrantedAuthority::new).toList();
        return new UsernamePasswordAuthenticationToken(id, token, list);
    }

    public LocalDateTime getExpiration(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        if (claims.get("exp") == null) {
            throw new RuntimeException("This is invalid token");
        }
        return claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public String resolveToken(String token) {
        if(StringUtils.hasText(token) && token.startsWith(AUTH)) {
            return token.substring(7);
        }
        return null;
    }

    private Key signingKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
