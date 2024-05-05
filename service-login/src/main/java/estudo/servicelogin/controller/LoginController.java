package estudo.servicelogin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.servicelogin.dto.User;
import estudo.servicelogin.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("login")
public class LoginController {

    private final UserService userService;

    @GetMapping("{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.getUser(email).get());
    }
    

}
