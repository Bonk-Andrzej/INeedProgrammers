package com.bonkAndrzej.iNeedProgrammers.security.jwtConfig;

import com.bonkAndrzej.iNeedProgrammers.util.CustomProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private  CustomProperties customProperties;
    private static final String AUTHORITIES_KEY = "auth";
    private Key key;
    private long tokenValidityInMilliseconds;
    private long tokenValidityInMillisecondsForRememberMe;
//    private String base64Secret = customProperties.getBase64Secret();
    private String base64Secret = "N2ViNjkyNmFkMGZiZjg2MjE5N2QzZDg2MmVmYjgzM2U0YzY0ZWNkMWYyNGQzMz" +
        "AyOTYzOTY2OTVlYWExYmUwZGRhOTY2NWM4MDRmZTE2NGI4N2U2MTY2YmRmNDRjMzlkMjY3YWQ3MGU3MjcxMGQ2N2Y3ODQ0YjcxOTE0ODQxMDI=";

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes;
        if (!StringUtils.isEmpty(base64Secret)) {
            log.warn("Warning: the JWT key used is not Base64-encoded. " +
                    "Recommend using the `security.authentication.jwt.base64-secret` key for optimum security.");
            keyBytes = base64Secret.getBytes(StandardCharsets.UTF_8);
        } else {
            log.debug("Using a Base64-encoded JWT secret key");
            keyBytes = Decoders.BASE64.decode(base64Secret);
        }
        key = Keys.hmacShaKeyFor(keyBytes);
        tokenValidityInMilliseconds = 1000 * 86400;
        tokenValidityInMillisecondsForRememberMe = 1000 * 2592000;
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}
