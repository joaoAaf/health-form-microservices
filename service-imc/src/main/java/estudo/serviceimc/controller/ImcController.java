package estudo.serviceimc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.serviceimc.dto.ImcData;
import estudo.serviceimc.service.ImcService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("imc")
public class ImcController extends BaseController {

    private final ImcService imcService;

    @PostMapping
    public ResponseEntity<Object> getImc(@RequestBody @Valid ImcData imcData, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            var imc = imcService.fromDto(imcData);
            log(this, request.getMethod(), request.getRequestURI(), authentication.getName(),
                        HttpStatus.CREATED.value());
            return getResponse(imcService.toDto(imc), HttpStatus.CREATED);
        } catch (Exception e) {
            log(this, request.getMethod(), request.getRequestURI(), authentication.getName(),
                    HttpStatus.OK.value(), e.getMessage());
            return getResponse("Serviço indisponível", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
