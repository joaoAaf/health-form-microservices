package estudo.serviceusers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.serviceusers.service.AuthService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {

    private final AuthService authService;

    @GetMapping("{email}")
    public ResponseEntity<Object> getUser(@PathVariable String email) {
        try {
            var user = authService.findUserEmail(email/*URLDecoder.decode(email, StandardCharsets.UTF_8)*/);
            if (user.isPresent()) {
                return getResponse(user.get(), HttpStatus.OK);
            }
            return getResponse("Este usuário não existe.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return getResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
