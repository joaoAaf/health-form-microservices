package estudo.servicelogin.request;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import estudo.servicelogin.dto.User;

@FeignClient(name = "service-users")
public interface UserRequest {

    @GetMapping("/auth/{email}")
    Optional<User> getUser(@PathVariable String email);

}