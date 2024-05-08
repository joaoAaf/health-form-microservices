package estudo.serviceusers.service;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256("essaéapalavrasecreta"))
                    .withIssuer("User")
                    .build().verify(token).getSubject();
        } catch (Exception e) {
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

}
