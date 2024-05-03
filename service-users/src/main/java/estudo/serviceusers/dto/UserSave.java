package estudo.serviceusers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserSave(@NotBlank String name, @NotBlank @Email String email, @NotBlank String pass) {

}
