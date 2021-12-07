package com.example.jwtserving.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {
    private final String jwtSecret;
    private final int jwtDuration;

    @Autowired
    public JwtProvider(@Value("${jwt.secret}") String jwtSecret, @Value("${jwt.duration}") int jwtDuration) {
        this.jwtSecret = jwtSecret;
        this.jwtDuration = jwtDuration;
    }

    public String generateToken(String login, Set<GrantedAuthority> authorities) {
        Date date = Date.from(LocalDate.now()
                .plusDays(jwtDuration)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .claim("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            log.error("Token is successfully validated");
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("Token expired");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported jwt");
        } catch (MalformedJwtException e) {
            log.error("Malformed jwt");
        } catch (SignatureException e) {
            log.error("Invalid signature");
        } catch (Exception e) {
            log.error("Invalid token");
        }
        return false;
    }

    public Map<String, String> getUserPropertiesFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return Map.of(
                "login", claims.getSubject(),
                "authirities", (String) claims.get("authorities")
        );
    }
}
