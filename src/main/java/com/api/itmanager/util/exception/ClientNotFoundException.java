package com.api.itmanager.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Client not found with ID " + id);
    }

    public ClientNotFoundException(String cnpj) {
        super("Client not found with CNPJ " + cnpj);
    }
}
