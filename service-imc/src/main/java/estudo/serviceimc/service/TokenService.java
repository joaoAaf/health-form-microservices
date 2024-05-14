package estudo.serviceimc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${env-variables.jwtSecret}")
    private String secret;
    
    public Optional<String> loggedId(String token) {
        try {
            return Optional.of(JWT.require(Algorithm.HMAC256(secret))
            .build().verify(token).getSubject());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
