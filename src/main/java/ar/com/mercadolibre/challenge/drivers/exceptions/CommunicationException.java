package ar.com.mercadolibre.challenge.drivers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


public class CommunicationException extends ResponseStatusException {
   public CommunicationException (String message) {
       super(HttpStatus.NOT_FOUND, message);
   }
}