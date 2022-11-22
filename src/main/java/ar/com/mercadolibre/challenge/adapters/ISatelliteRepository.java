package ar.com.mercadolibre.challenge.adapters;


import ar.com.mercadolibre.challenge.entities.Satellite;

import java.util.List;
import java.util.Optional;

public interface ISatelliteRepository {

    Optional<Satellite> findSatelliteByName(String name);

    Satellite save(Satellite s);

    List<Satellite> findAll();
}
