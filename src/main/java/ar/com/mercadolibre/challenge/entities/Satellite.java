package ar.com.mercadolibre.challenge.entities;

import ar.com.mercadolibre.challenge.drivers.util.JsonConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table( name = "satellites")
public class Satellite {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Convert(converter = JsonConverter.class)
    private Position position;
    private String message;
    private Float distance;


    public Satellite( String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public List<String> getMessage() {
        return message != null ?  Arrays.stream(message.split(",")).map(s -> s.replace(" ","")).collect(Collectors.toList()): null;
    }

    public void setMessage(List<String> message) {
        this.message = String.join(", ",message);
    }

}
