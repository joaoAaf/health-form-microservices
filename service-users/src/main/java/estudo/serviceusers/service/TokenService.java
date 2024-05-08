package estudo.serviceusers.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {
    
    public Optional<String> loggedId(String token) {
        try {
            return Optional.of(JWT.require(Algorithm.HMAC256("essaéapalavrasecreta"))
            .build().verify(token).getSubject());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
