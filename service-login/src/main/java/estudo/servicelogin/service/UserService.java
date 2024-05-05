package estudo.servicelogin.service;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import estudo.servicelogin.dto.User;
import estudo.servicelogin.request.UserRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Scope("singleton")
@Service
public class UserService {

    private final UserRequest request;

    public Optional<User> getUser(String email) {
        var response = request.getUser(email);
        if (response.isPresent()) {
            return response;
        }
        return Optional.empty();
    }

}
