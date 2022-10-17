package com.example.one.jwt;

import com.example.one.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final long validTime;
    private Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.validTime}") long validTime) {
        this.secret = secret;
        this.validTime = validTime;
    }

    public String createAccessToken(UserInfo userInfo) {
        String authority = userInfo.getRole();
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
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//        if (claims.get("auth") == null) {
//            throw new RuntimeException("This is invalid token");
//        }
//        Collection<? extends GrantedAuthority> authority = claims.get("auth");
//
//        UserDetails userDetails = new User(claims.getSubject(),"", authority);
        return null;
    }
}
