package center.unit.letter.domain.auth;

import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.shared.config.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtProperties jwtProperties;
    private final UserFacade userFacade;

    public String generateAccessToken(String phoneNumber) {
        return generateToken(phoneNumber, jwtProperties.getAccessExpirationTime());
    }

    private String generateToken(String phoneNumber, Long time) {
        Claims claims = Jwts
                                .claims()
                                .add("phone-number", phoneNumber)
                                .build();
        Date now = new Date();

        return Jwts.builder()
                       .setClaims(claims)
                       .setIssuedAt(now)
                       .setExpiration(new Date(now.getTime() + time))
                       .signWith(getSigningKey(jwtProperties.getSecretKey()), SignatureAlgorithm.HS256)
                       .compact();
    }

    public User getUser(String token) {
        return userFacade.getUser(getPhoneNumber(token));
    }

    public String getPhoneNumber(String phoneNumber) {
        return extractAllClaims(phoneNumber).get("phone-number", String.class);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                           .setSigningKey(getSigningKey(jwtProperties.getSecretKey()))
                           .build()
                           .parseClaimsJws(token)
                           .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
