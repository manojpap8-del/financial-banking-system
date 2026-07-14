package manoj.example.financial_banking_system.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.util.function.Function;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey =
            Keys.hmacShaKeyFor(
                    "mySuperSecretKeyForFinancialBankingSystemProject2026VerySecureKey".getBytes()
            );

    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();
    }

    private Claims extractAllClaims(String token) {

    JwtParser parser = Jwts.parser()
            .verifyWith(secretKey)
            .build();

    return parser
            .parseSignedClaims(token)
            .getPayload();
}

  public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);

}

public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

    Claims claims = extractAllClaims(token);

    return claimsResolver.apply(claims);
}

public Date extractExpiration(String token) {

    return extractClaim(token, Claims::getExpiration);

    

}
private boolean isTokenExpired(String token) {

    return extractExpiration(token).before(new Date());

}

public boolean isTokenValid(String token, String email) {

    String extractedEmail = extractEmail(token);

    return extractedEmail.equals(email) && !isTokenExpired(token);
}


}