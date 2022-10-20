package com.example.one.jwt;

import com.example.one.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.validTime}")
    private long validTime;
    private Key key;

    public String createAccessToken(UserInfo userInfo) {
        String authority = userInfo.getRole();
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Date now = new Date();
        Date exp = new Date(now.getTime() + validTime);

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(exp)
                .setSubject(userInfo.getId())
                .claim("auth", authority)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        if (claims.get("auth") == null) {
            throw new RuntimeException("This is invalid token");
        }
        String id = claims.getSubject();
        List<SimpleGrantedAuthority> list= Stream.of(claims.get("auth").toString()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(id, token, list);
    }

    public LocalDateTime getExpiration(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        if (claims.get("exp") == null) {
            throw new RuntimeException("This is invalid token");
        }
        return claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
