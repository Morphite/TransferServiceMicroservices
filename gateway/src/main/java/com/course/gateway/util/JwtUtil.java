package com.course.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.time}")
    private long tokenExpiredTime;

    public String createToken(List<String> roles, String subject) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("roles", roles);

        Date now = new Date();
        Date validityTokenTime = new Date(now.getTime() + tokenExpiredTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validityTokenTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return claims.getBody().getExpiration().before(new Date());
    }

    public String getTokenFromRequest(HttpServletRequest req) {
        String bearer = req.getHeader("Authorization");
        return bearer != null && bearer.startsWith("Bearer ") ? bearer.substring(7) : null;
    }


}
