package estudo.serviceimc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ImcData(
    @NotBlank(message = "'weight' deve ser preenchido") 
    @Positive(message = "'weight' deve maior que '0'")
    double weight, 
    @NotBlank(message = "'height' deve ser preenchido")
    @Positive(message = "'height' deve maior que '0'")
    double height) {

}
