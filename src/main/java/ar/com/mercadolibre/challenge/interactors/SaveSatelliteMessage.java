package ar.com.mercadolibre.challenge.interactors;

import ar.com.mercadolibre.challenge.adapters.ISatelliteRepository;
import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import ar.com.mercadolibre.challenge.entities.DTO.SatelliteDTO;
import ar.com.mercadolibre.challenge.entities.Satellite;

import java.util.Optional;

public class SaveSatelliteMessage {

    private ISatelliteRepository satelliteRepository;

    public SaveSatelliteMessage(ISatelliteRepository repository) {
        this.satelliteRepository = repository;
    }

    public void saveMessage(SatelliteDTO satelite) throws BadRequestException {
        Optional<Satellite> satelliteDb = satelliteRepository.findSatelliteByName(satelite.getName());

        if (satelliteDb.isEmpty())
            throw new BadRequestException("Satellite not found");

        satelliteDb.get().setMessage(satelite.getMessage());
        satelliteDb.get().setDistance(satelite.getDistance());
        satelliteRepository.save(satelliteDb.get());


    }
}
