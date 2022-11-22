package ar.com.mercadolibre.challenge.tdd;

import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import ar.com.mercadolibre.challenge.drivers.trilaterationService.DecoderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class DecoderServiceTest {

    DecoderService decoderService = new DecoderService();
    List<List<String>> messages = new ArrayList();

    List<List<String>> nonDecodableMsj = new ArrayList();

    @BeforeEach
    public void setUp(){
        messages.add(new ArrayList<String>(Arrays.asList("", "este", "es", "un", "mensaje")));
        messages.add(new ArrayList<String>(Arrays.asList("este", "", "un", "mensaje")));
        messages.add(new ArrayList<String>(Arrays.asList("", "", "es", "", "mensaje")));

       nonDecodableMsj.add(new ArrayList<String>(Arrays.asList("")));
    }

    @Test
    void decodeMessajeTest() throws BadRequestException {
        List<String> message = decoderService.getMessage(messages, new ArrayList<String>());
        assertTrue("este es un mensaje".equals(String.join(" ", message)));
    }

    @Test
    void decodeMessajeExceptionTest() {
        BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,() -> decoderService.getMessage(nonDecodableMsj, new ArrayList<String>()),
                "Error: message can't decode");
        assertTrue(exception.getMessage().contains("Error: message can't decode"));
    }

}


