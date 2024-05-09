package estudo.serviceusers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.serviceusers.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        return getResponse(userService.findUsers(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getUser(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            var user = userService.findUserId(authentication.getName());
            if (user.isPresent()) {
                log(this, request.getMethod(), request.getRequestURI(), authentication.getName(),
                        HttpStatus.OK.value());
                return getResponse(user.get(), HttpStatus.OK);
            }
            log(this, request.getMethod(), request.getRequestURI(), authentication.getName(),
                        HttpStatus.OK.value(), "Este usuário não existe");
            return getResponse("Este usuário não existe", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log(this, request.getMethod(), request.getRequestURI(), authentication.getName(),
                        HttpStatus.OK.value(), e.getMessage());
            return getResponse("Serviço indisponível", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
