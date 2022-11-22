package ar.com.mercadolibre.challenge.adapters;

import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.List;

public interface IMessageDecoderService {

    List<String> getMessage(List<List<String>> messages, List<String> msjDecode) throws BadRequestException;

}
