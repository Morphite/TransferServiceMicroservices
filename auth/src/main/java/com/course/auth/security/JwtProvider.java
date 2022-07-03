package com.course.auth.security;

import com.course.auth.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expired}")
    private long tokenExpiredTime;

//    @Autowired
//    private UserDetailsService userDetailsService;

    public String createToken(String login, Role role) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("roles", role.name());

        Date now = new Date();
        Date validityTokenTime = new Date(now.getTime() + tokenExpiredTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validityTokenTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

//    public String getTokenFromRequest(HttpServletRequest req) {
//        String bearer = req.getHeader("Authorization");
//        return bearer != null && bearer.startsWith("Bearer ") ? bearer.substring(7) : null;
//    }

//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String login = getUsername(token);
//        return login.equals(userDetails.getUsername()) && !isExpired(token);
//    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private <T> T getClaim(String token, Function<Claims, T> claimResolver) {
        return claimResolver.apply(getClaims(token));
    }

    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }


}
