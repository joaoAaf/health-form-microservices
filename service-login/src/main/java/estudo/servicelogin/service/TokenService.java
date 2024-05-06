package estudo.servicelogin.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import estudo.servicelogin.dto.User;

@Service
public class TokenService {

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer(user.getClass().getSimpleName())
                .withSubject(user.email())
                .withClaim("id", user.id())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("essaéapalavrasecreta"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("essaéapalavrasecreta"))
                .withIssuer("User")
                .build().verify(token).getSubject();
    }

    public String getClaim(String token) {
        return JWT.require(Algorithm.HMAC256("essaéapalavrasecreta"))
                .withIssuer("User")
                .build().verify(token).getClaim("id").asString();
    }

}
