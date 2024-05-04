package estudo.serviceusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import estudo.serviceusers.dto.ResponseData;

public class BaseController {

    @Autowired
    private Environment environment;

    private String getPort() {
        return environment.getProperty("local.server.port");
    }

    protected ResponseEntity<Object> getResponse(String msg, HttpStatus status) {
        return new ResponseEntity<>(ResponseData
            .builder()
            .msg(msg)
            .port(getPort())
            .build()
            , status);
    }

    protected ResponseEntity<Object> getResponse(Object data, String msg, HttpStatus status) {
        return new ResponseEntity<>(ResponseData
        .builder()
        .data(data)
        .msg(msg)
        .port(getPort())
        .build()
        , status);
    }

    protected ResponseEntity<Object> getResponse(Object data, HttpStatus status) {
        return new ResponseEntity<>(ResponseData
        .builder()
        .data(data)
        .port(getPort())
        .build()
        , status);
    }

}
