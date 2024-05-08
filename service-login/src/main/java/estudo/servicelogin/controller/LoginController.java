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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {

    private final TokenService tokenService;
    private final AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid Login login) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.email(),
                    login.pass());
            Authentication auth = this.authManager.authenticate(authToken);
            var user = (User) auth.getPrincipal();
            return getResponse(tokenService.generateToken(user), "Login realizado com sucesso", HttpStatus.OK);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return getResponse("Usuário ou senha inválidos", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return getResponse("Serviço indisponível", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
