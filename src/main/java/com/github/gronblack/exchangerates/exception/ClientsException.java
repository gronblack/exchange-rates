package com.github.gronblack.exchangerates.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientsException extends ResponseStatusException {
    public ClientsException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    @Override
    public String getMessage() {
        return getReason();
    }
}
