package ar.com.mercadolibre.challenge.entities.DTO;

import ar.com.mercadolibre.challenge.entities.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO {

    private Position position;
    private String message;

    public ResponseDTO(Position position, String message) {
        this.position = position;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "position=" + position +
                ", message='" + message + '\'' +
                '}';
    }
}
