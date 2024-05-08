package estudo.serviceusers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.serviceusers.service.UserService;
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
    public ResponseEntity<Object> getUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var user = userService.findUserId(authentication.getName());
            if (user.isPresent()) {
                return getResponse(user.get(), HttpStatus.OK);
            }
            return getResponse("Este usuário não existe.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return getResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


}
