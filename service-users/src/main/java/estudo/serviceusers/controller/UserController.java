package estudo.serviceusers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.serviceusers.dto.UserMod;
import estudo.serviceusers.dto.UserSave;
import estudo.serviceusers.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid UserSave userSave, HttpServletRequest request) {
        try {
            if (userService.findUserEmail(userSave.email()).isEmpty()) {
                var user = userService.saveUser(userSave);
                log(this, request.getMethod(), request.getRequestURI(), "Novo id cadastrado",
                        HttpStatus.CREATED.value());
                return getResponse(user, HttpStatus.CREATED);
            }
            log(this, request.getMethod(), request.getRequestURI(), "Não cadastrado",
                    HttpStatus.BAD_REQUEST.value(), "Já existe um usuário com este email");
            return getResponse("Já existe um usuário com este email", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log(this, request.getMethod(), request.getRequestURI(), "Não cadastrado",
                    HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
            return getResponse("Serviço indisponível", HttpStatus.SERVICE_UNAVAILABLE);
        }
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

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody @Valid UserMod newUser, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (userService.findUserId(authentication.getName()).isPresent()) {
                var user = userService.updateUser(newUser, authentication.getName());
                log(this, request.getMethod(), request.getRequestURI(), authentication.getName(),
                        HttpStatus.OK.value());
                return getResponse(user, HttpStatus.OK);
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

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (userService.findUserId(authentication.getName()).isPresent()) {
                userService.deleteUser(authentication.getName());
                log(this, request.getMethod(), request.getRequestURI(), authentication.getName(),
                        HttpStatus.NO_CONTENT.value());
                return getResponse(null, HttpStatus.NO_CONTENT);
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
