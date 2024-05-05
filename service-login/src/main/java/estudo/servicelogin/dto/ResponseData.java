package estudo.servicelogin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;

@Builder
@JsonInclude(value = Include.NON_NULL)
public record ResponseData(Object data, String msg, String port) {

}