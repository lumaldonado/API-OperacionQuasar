package ar.com.mercadolibre.challenge.drivers.repositories;

import ar.com.mercadolibre.challenge.adapters.ISatelliteRepository;
import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.entities.Satellite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MockSatelliteRepository implements ISatelliteRepository {

    private Satellite satellite = new Satellite();
    private List<Satellite> listSatellite = new ArrayList<>();

    @Override
    public Optional<Satellite> findSatelliteByName(String name) {
        if (Objects.equals(name, "kenobi")){
        Satellite satellite = new Satellite("kenobi", new Position(5.0f, -6.0f), "hello");
        return Optional.of(satellite);}
        else{
        return Optional.empty();
        }
    }

    @Override
    public Satellite save(Satellite s) {
        return this.satellite = s;
    }

    @Override
    public List<Satellite> findAll() {
        listSatellite.add(new Satellite("kenobi", new Position(5.0f, -6.0f)));
        listSatellite.add(new Satellite("sato", new Position(13.0f, -15.0f)));
        listSatellite.add(new Satellite("skywalker", new Position(21.0f, -3.0f)));
        return listSatellite;
    }
}
