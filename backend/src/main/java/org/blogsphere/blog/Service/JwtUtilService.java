package org.blogsphere.blog.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.blogsphere.blog.Enum.ERole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtUtilService {
    @Value("${backend.jwt.secret}")
    private String secret;

    @Value("${backend.jwt.expiration}")
    private Long expiration;

    private Key key;

    @PostConstruct
    public void initKey()
    {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username , Set<ERole> roles, String tokenType){
        Map<String, Object> claims = new HashMap<>();

        claims.put("username", username);
        claims.put("roles", roles);

        Long expMillis = "ACCESS".equalsIgnoreCase(tokenType)
            ? expiration * 1000
            : expiration * 1000 *5;


        return Jwts
            .builder()
            .claims(claims)
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expMillis))
            .signWith(key)
            .compact();
    }

    public <T> T extractClaim(String token , Function<Claims, T> claimsResolver){
        return claimsResolver.apply(
            Jwts.parser()
            .decryptWith(new SecretKey() {

                @Override
                public String getAlgorithm() {
                    return key.getAlgorithm();
                }

                @Override
                public byte[] getEncoded() {
                    return key.getEncoded();
                }

                @Override
                public String getFormat() {
                    return key.getFormat();
                }
                
            })
            .build()
            .parseSignedClaims(token)
            .getPayload()
        );
    }
    public boolean isTokenValid(String token , String username){
        return (username.equals(getSubject(token)) && !isTokenExpired(token));
    }

    public Date getExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    
    public String getSubject(String token){
        return extractClaim(token, Claims::getSubject);
    }
    
    public boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }
}
