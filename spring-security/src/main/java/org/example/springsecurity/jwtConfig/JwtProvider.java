package org.example.springsecurity.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
  private String secretKey = "this-is-a-secret-key-for-jwt-security";

  public String generateToken(String username, Map<String,Object> claims){
    return Jwts
        .builder()
        .setSubject(username)
        .setClaims(claims)
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
        .compact();

  }

  public String getUsername(String token){
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaims(String token){
    return
        Jwts
            .parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseClaimsJwt(token)
            .getBody();
  }

}
