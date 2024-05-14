package estudo.servicelogin.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import estudo.servicelogin.dto.User;

@Service
public class TokenService {

    @Value("${env-variables.jwtSecret}")
    private String secret;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.id())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256(secret));
    }

}
