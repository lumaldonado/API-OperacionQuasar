package ar.com.mercadolibre.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ar.com.mercadolibre.challenge.drivers")
public class ChallengeApplication {

	public static void main(String[] args) {

		SpringApplication.run(ChallengeApplication.class, args);
	}

}
