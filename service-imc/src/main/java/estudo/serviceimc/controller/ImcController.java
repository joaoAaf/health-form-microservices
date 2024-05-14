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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApiResponse(content = @Content(examples = { @ExampleObject(name = "Sucesso", value = """
        {
          "data": {
            "imc": "double",
            "msg": "String",
            "diet": "String"
          },
          "port": "String"
        }""") }))
@RestController
@RequestMapping("imc")
public class ImcController extends BaseController {

    private final ImcService imcService;

    @Operation(summary = "Calcula o IMC e retorna a mensagem de acordo com o resultado")
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
