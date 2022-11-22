package ar.com.mercadolibre.challenge.entities.DTO;

import ar.com.mercadolibre.challenge.entities.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
public class SatelliteDTO {

    private String name;
    private Float distance;
    private Position position;
    private ArrayList<String> message;

    public SatelliteDTO(String name, Float distance, ArrayList<String> message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public SatelliteDTO(Float distance, ArrayList<String> message) {
        this.distance = distance;
        this.message = message;
    }

    public SatelliteDTO(String name, Position position, ArrayList<String> message) {
        this.name = name;
        this.position = position;
        this.message = message;
    }

}
