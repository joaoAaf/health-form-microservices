package estudo.serviceusers.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.serviceusers.dto.UserAuth;
import estudo.serviceusers.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Hidden
@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {

    private final AuthService authService;

    @GetMapping("{email}")
    public Optional<UserAuth> getUser(@PathVariable String email) {
        return authService.findUserEmail(email);
    }

}
