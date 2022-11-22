package ar.com.mercadolibre.challenge.drivers.repositories;

import ar.com.mercadolibre.challenge.adapters.ISatelliteRepository;
import ar.com.mercadolibre.challenge.entities.Satellite;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SatelliteRepository implements ISatelliteRepository {

    private JPASatelliteRepository repository;

    public SatelliteRepository(JPASatelliteRepository repo){
        this.repository = repo;
    }

    @Override
    public Optional<Satellite> findSatelliteByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Satellite save(Satellite s) {
        return repository.save(s) ;
    }

    @Override
    public List<Satellite> findAll() {
        return repository.findAll();
    }
}
