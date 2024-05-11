package estudo.serviceimc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ImcData(@NotBlank @Positive double weight, @NotBlank @Positive double height) {

}
