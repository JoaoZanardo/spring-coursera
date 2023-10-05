package com.zanardo.todo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zanardo.todo.models.User.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
//    @Value("${token_secret}")
    private String secret = "384fm7n";

    private final Algorithm algorithm = Algorithm.HMAC256(this.secret);

    public String generate(UserModel user) throws JWTCreationException {
        return JWT.create()
                .withIssuer("todo-api")
                .withSubject(user.getAccount())
                .withExpiresAt(this.genExperiantionDate())
                .sign(this.algorithm);
    }

    public String validate(String token) throws JWTVerificationException {
        return JWT.require(this.algorithm)
                .withIssuer("todo-api")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant genExperiantionDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
