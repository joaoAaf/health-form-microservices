package estudo.servicelogin.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import estudo.servicelogin.dto.ResponseData;

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

    protected void log(Object obj, String method, String uri, String id, int status) {
        LoggerFactory.getLogger(obj.getClass())
                .info(String.format("Requisição: %s, URI: %s, Id do Usuário: %s, Status: %d",
                        method,
                        uri,
                        id,
                        status));
    }

    protected void log(Object obj, String method, String uri, String id, int status, String erro) {
        LoggerFactory.getLogger(obj.getClass())
                .error(String.format("Requisição: %s, URI: %s, Id do Usuário: %s, Status: %d,\nErro: %s",
                        method,
                        uri,
                        id,
                        status,
                        erro));
    }

    protected void log(Object obj, String method, String uri, int status, String erro) {
        LoggerFactory.getLogger(obj.getClass())
                .error(String.format("Requisição: %s, URI: %s, Status: %d,\nErro: %s",
                        method,
                        uri,
                        status,
                        erro));
    }

}
