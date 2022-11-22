package ar.com.mercadolibre.challenge.drivers.repositories;

import ar.com.mercadolibre.challenge.entities.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPASatelliteRepository extends JpaRepository<Satellite, Integer> {

    Optional<Satellite> findByName(String name);
}
