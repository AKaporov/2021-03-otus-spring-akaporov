package ru.otus.spring.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.jwt.exception.JwtProviderException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {
    private static final String TOKEN_EXPIRED = "Token expired: ";
    private static final String UNSUPPORTED_JWT = "Unsupported jwt: ";
    private static final String MALFORMED_JWT = "Malformed jwt: ";
    private static final String INVALID_SIGNATURE_JWT = "Invalid signature jwt: ";
    private static final String INVALID_TOKEN = "Invalid token: ";
    private static final String CHECK_LOGIN_OR_PASSWORD = "Check login or password!";

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String login) {
        if (login == null) {
            throw new JwtProviderException(CHECK_LOGIN_OR_PASSWORD);
        }

        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            new JwtProviderException(TOKEN_EXPIRED + expEx.getCause());
        } catch (UnsupportedJwtException unsEx) {
            new JwtProviderException(UNSUPPORTED_JWT + unsEx.getCause());
        } catch (MalformedJwtException mlfEx) {
            new JwtProviderException(MALFORMED_JWT + mlfEx.getCause());
        } catch (SignatureException sgnEx) {
            new JwtProviderException(INVALID_SIGNATURE_JWT + sgnEx.getCause());
        } catch (Exception e) {
            new JwtProviderException(INVALID_TOKEN + e.getCause());
        }

        return false;
    }
}
