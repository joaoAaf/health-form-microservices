package estudo.serviceusers.dto;

import lombok.Builder;

@Builder
public record UserView(String id, String name, String email) {

}
