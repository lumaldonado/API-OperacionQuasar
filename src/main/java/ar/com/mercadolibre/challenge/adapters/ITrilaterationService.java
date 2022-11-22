package ar.com.mercadolibre.challenge.adapters;

import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import ar.com.mercadolibre.challenge.entities.Position;

import java.util.List;

public interface ITrilaterationService {
    Position getLocation(List<Float> distances, List<Position> positions) throws BadRequestException;
}
