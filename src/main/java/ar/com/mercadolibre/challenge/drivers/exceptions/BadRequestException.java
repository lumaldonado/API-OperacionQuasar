package ar.com.mercadolibre.challenge.drivers.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }

}
