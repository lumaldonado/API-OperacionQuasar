package ar.com.mercadolibre.challenge.drivers.controller;

import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import ar.com.mercadolibre.challenge.drivers.exceptions.CommunicationException;
import ar.com.mercadolibre.challenge.entities.DTO.ResponseDTO;
import ar.com.mercadolibre.challenge.entities.DTO.SatelliteDTO;
import ar.com.mercadolibre.challenge.interactors.MessageDecoder;
import ar.com.mercadolibre.challenge.interactors.SaveSatelliteMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SatelliteController {


    private MessageDecoder decoder;

    private SaveSatelliteMessage saveMessage;

    public SatelliteController(MessageDecoder decoder,SaveSatelliteMessage saveMessage) {
        this.decoder = decoder;
        this.saveMessage = saveMessage;
    }

    @PostMapping("/topsecret")
    public ResponseDTO processMessages(@RequestBody List<SatelliteDTO> satellites) throws CommunicationException {
        try {
            return decoder.processMessages(satellites);
        }
        catch(Exception e){
            throw new CommunicationException("Error: The message or position could not be received");
        }
    }

    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<String> saveMessage(@PathVariable(value = "satellite_name") String name, @RequestBody SatelliteDTO sateliteDTO) throws BadRequestException {
        sateliteDTO.setName(name);
        saveMessage.saveMessage(sateliteDTO);
        return ResponseEntity.ok("Message saved");
    }


    @GetMapping("/topsecret_split/{satellite_name}")
    public ResponseDTO decodeMessageAndGetDistanceForOneSatellite(@PathVariable(value = "satellite_name") String name) throws BadRequestException {
        return decoder.getMessageAndLocationForOneSatellite(name);
    }

    @GetMapping("/topsecret_split")
    public ResponseDTO decodeMessageAndGetDistance() throws CommunicationException  {
        try {
            return decoder.getMessageAndLocation();
        }
        catch(Exception e){
            throw new CommunicationException("Error: Insufficient data");
        }
    }


}
