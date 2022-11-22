package ar.com.mercadolibre.challenge.drivers.configuration;

import ar.com.mercadolibre.challenge.adapters.IMessageDecoderService;
import ar.com.mercadolibre.challenge.adapters.ISatelliteRepository;
import ar.com.mercadolibre.challenge.adapters.ITrilaterationService;
import ar.com.mercadolibre.challenge.interactors.MessageDecoder;
import ar.com.mercadolibre.challenge.interactors.SaveSatelliteMessage;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public MessageDecoder getDecoderInteractor(ITrilaterationService trilaterationService, IMessageDecoderService decoderService, ISatelliteRepository satelliteRepository){
        return new MessageDecoder(trilaterationService,decoderService,satelliteRepository);
    }
    @Bean
    public SaveSatelliteMessage getSaveMessageInteractor(ISatelliteRepository satelliteRepository){
        return new SaveSatelliteMessage(satelliteRepository);
    }
}
