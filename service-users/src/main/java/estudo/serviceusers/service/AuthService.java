package estudo.serviceusers.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import estudo.serviceusers.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService extends BaseService {

    private final UserRepository userRepo;
    
    public Optional<Object> findUserEmail(String email) {
        var user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            return Optional.of(toDtoAuth(user.get()));
        }
        return Optional.empty();
    }

}
