package ar.com.mercadolibre.challenge.interactors;

import ar.com.mercadolibre.challenge.adapters.IMessageDecoderService;
import ar.com.mercadolibre.challenge.adapters.ISatelliteRepository;
import ar.com.mercadolibre.challenge.adapters.ITrilaterationService;
import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import ar.com.mercadolibre.challenge.entities.DTO.ResponseDTO;
import ar.com.mercadolibre.challenge.entities.DTO.SatelliteDTO;
import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.entities.Satellite;

import java.util.*;
import java.util.stream.Collectors;

public class MessageDecoder {

    private ITrilaterationService trilaterationService;

    private IMessageDecoderService decoderService;

    private ISatelliteRepository satelliteRepository;

    public MessageDecoder(ITrilaterationService trilaterationService, IMessageDecoderService decoderService, ISatelliteRepository satelliteRepository) {
        this.trilaterationService = trilaterationService;
        this.decoderService = decoderService;
        this.satelliteRepository = satelliteRepository;
    }

    public ResponseDTO processMessages(List<SatelliteDTO> satellites) throws BadRequestException {
        if(satellites.size() < 3 )
            throw new BadRequestException("Error insufficient satellites");
        return decodeMessageAndGetLocation(satellites);

    }

    public ResponseDTO getMessageAndLocation() throws BadRequestException {
        List<Satellite> satellites = satelliteRepository.findAll();
        if( satellites.size() < 3 || satellites.stream().anyMatch(satelite -> satelite.getMessage() == null))
            throw new BadRequestException("Error insufficient satellites or one message is empty");
            List<SatelliteDTO> satellitesDto = new ArrayList<>();
            satellites.forEach(satelite -> satellitesDto.add(new SatelliteDTO(satelite.getName(), satelite.getDistance(), (ArrayList<String>) satelite.getMessage())));
            return decodeMessageAndGetLocation(satellitesDto);
    }

    public ResponseDTO getMessageAndLocationForOneSatellite(String name) throws BadRequestException {
        Optional<Satellite> satelliteOptional = satelliteRepository.findSatelliteByName(name);
        if(!satelliteOptional.isPresent()){
            throw new BadRequestException("Satellite not found");
        }
        Satellite satellite = satelliteOptional.get();
        SatelliteDTO satelliteDTO = new  SatelliteDTO(satellite.getName(), satellite.getPosition(), (ArrayList<String>) satellite.getMessage());

        return new ResponseDTO(satelliteDTO.getPosition(), String.join(" ", satellite.getMessage()));

    }



    private ResponseDTO decodeMessageAndGetLocation(List<SatelliteDTO> satellites) throws BadRequestException {
        List<String> messages = decoderService.getMessage(satellites.stream().map(SatelliteDTO::getMessage).collect(Collectors.toList()), new ArrayList<>());

        List<Float> distances = satellites.stream().map(SatelliteDTO::getDistance).collect(Collectors.toList());
        List<Position> postions = findSatellites(satellites).stream().map(Satellite::getPosition).collect(Collectors.toList());
        Position position = trilaterationService.getLocation(distances, postions);

        return new ResponseDTO(position, String.join(" ", messages));
    }

    private List<Satellite> findSatellites(List<SatelliteDTO> satellites) throws BadRequestException {
        List<Satellite> satelitesFromDB = new ArrayList<>();
        satellites.forEach(sateliteDTO -> {
                    Optional<Satellite> satelite = satelliteRepository.findSatelliteByName(sateliteDTO.getName());
                    satelite.ifPresent(satelitesFromDB::add);
                }

        );
        if (satelitesFromDB.size() < 3)
            throw new BadRequestException("Error insufficient satellites");

        return satelitesFromDB;

    }
}
