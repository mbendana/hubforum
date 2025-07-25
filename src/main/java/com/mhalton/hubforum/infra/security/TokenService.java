package com.mhalton.hubforum.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mhalton.hubforum.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService
{
    private final String ISSUER = "Hub Forum";
    @Value("${hubforum.security.token.secret}")
    private String secret;

    private Instant expirationDate()
    {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

    public String generateToken(User user)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                      .withIssuer(ISSUER)
                      .withSubject(user.getUsername())
                      .withExpiresAt(expirationDate())
                      .sign(algorithm);
        }
        catch (Exception exception)
        {
            throw new RuntimeException("Error generating JWT token", exception);
        }
    }

    public String getSubject(String jwtToken)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                      .withIssuer(ISSUER)
                      .build()
                      .verify(jwtToken)
                      .getSubject();
        }
        catch (JWTVerificationException exception)
        {
            throw new RuntimeException("JWT token invalid or expired", exception);
        }
    }
}
