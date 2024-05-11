package estudo.serviceimc.dto;

import jakarta.validation.constraints.Positive;

public record ImcData( 
    @Positive(message = "'weight' deve maior que '0'")
    double weight, 
    @Positive(message = "'height' deve maior que '0'")
    double height) {

}
