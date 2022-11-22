package ar.com.mercadolibre.challenge.drivers.configuration;

import ar.com.mercadolibre.challenge.drivers.repositories.SatelliteRepository;
import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.entities.Satellite;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements ApplicationRunner{

    private SatelliteRepository repository;

    public InitData(SatelliteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        repository.save(new Satellite("kenobi",new Position(-500,-200)));
        repository.save(new Satellite("skywalker",new Position(100,-100)));
        repository.save(new Satellite("sato",new Position(500,100)));

    }
}

