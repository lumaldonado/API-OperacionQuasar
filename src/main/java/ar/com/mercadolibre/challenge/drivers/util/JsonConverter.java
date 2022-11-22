package ar.com.mercadolibre.challenge.drivers.util;

import ar.com.mercadolibre.challenge.entities.Position;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.persistence.AttributeConverter;

public class JsonConverter implements AttributeConverter<Position, String> {

    @Override
    public String convertToDatabaseColumn(Position location) {
        String locationJson = null;
        try {
            locationJson = Jsons.getObjectMapper().writeValueAsString(location);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return locationJson;
    }

    @Override
    public Position convertToEntityAttribute(String s) {
        Position location = null;
        try {
            location =  Jsons.objectFromString(Position.class,s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return location;
    }
}
