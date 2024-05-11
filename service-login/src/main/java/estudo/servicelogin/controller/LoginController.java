package estudo.servicelogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.servicelogin.dto.Login;
import estudo.servicelogin.dto.User;
import estudo.servicelogin.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {

    private final TokenService tokenService;
    private final AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid Login login, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.email(),
                    login.pass());
            Authentication auth = this.authManager.authenticate(authToken);
            var user = (User) auth.getPrincipal();
            var token = tokenService.generateToken(user);
            log(this, request.getMethod(), request.getRequestURI(), user.id(),
                        HttpStatus.CREATED.value());
            return getResponse(token, "Login realizado com sucesso", HttpStatus.CREATED);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            log(this, request.getMethod(), request.getRequestURI(),
                        HttpStatus.UNAUTHORIZED.value(), e.getMessage());
            return getResponse("Usuário ou senha inválidos", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log(this, request.getMethod(), request.getRequestURI(),
                        HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
            return getResponse("Serviço indisponível", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
