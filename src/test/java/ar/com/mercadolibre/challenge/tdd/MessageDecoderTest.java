package ar.com.mercadolibre.challenge.tdd;


import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import ar.com.mercadolibre.challenge.drivers.repositories.MockSatelliteRepository;
import ar.com.mercadolibre.challenge.drivers.trilaterationService.DecoderService;
import ar.com.mercadolibre.challenge.drivers.trilaterationService.NonLinearTrilaterationSolver;
import ar.com.mercadolibre.challenge.entities.DTO.ResponseDTO;
import ar.com.mercadolibre.challenge.entities.DTO.SatelliteDTO;
import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.interactors.MessageDecoder;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


@SpringBootTest
public class MessageDecoderTest {

    DecoderService decoderService = new DecoderService();
    NonLinearTrilaterationSolver solver = new NonLinearTrilaterationSolver();
    MockSatelliteRepository mock = new MockSatelliteRepository();

    private List<SatelliteDTO> listSatellite = new ArrayList<>();
    private List<String> message = new ArrayList<>();
    MessageDecoder messageDecoder = new MessageDecoder(solver,decoderService,mock);

    @BeforeEach
    public void setUp(){
        message.add("hola");
        message.add("");
        message.add("estas");

        listSatellite.add(new SatelliteDTO("kenobi", new Position(5.0f, -6.0f), (ArrayList<String>) message));
        listSatellite.add(new SatelliteDTO("sato", new Position(13.0f, -15.0f), (ArrayList<String>) message));

    }

    @Test
    void getMessageAndLocationForOneSatellite() throws BadRequestException {
        ResponseDTO responseDTO = messageDecoder.getMessageAndLocationForOneSatellite("kenobi");
        AssertionsForClassTypes.assertThat(responseDTO).isNotNull();
    }

    @Test
    void getMessageAndLocationForOneSatelliteException() throws BadRequestException {
        BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,() -> messageDecoder.getMessageAndLocationForOneSatellite("sato"),
                "Satellite not found");
        assertTrue(exception.getMessage().contains("Satellite not found"));
    }

    @Test
    void findSatelliteException() throws BadRequestException {
        BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,() -> messageDecoder.processMessages(listSatellite),
                "Error insufficient satellites");
        assertTrue(exception.getMessage().contains("Error insufficient satellites"));
    }


    @Test
    void getMessageAndLocationExceptionTest() {
        BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,() -> messageDecoder.getMessageAndLocation(),
                "Error insufficient satellites or one message is empty");
        assertTrue(exception.getMessage().contains("Error insufficient satellites or one message is empty"));
    }
}
