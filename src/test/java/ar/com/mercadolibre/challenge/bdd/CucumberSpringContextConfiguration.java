package ar.com.mercadolibre.challenge.bdd;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
@CucumberContextConfiguration
@ContextConfiguration(loader = SpringBootContextLoader.class)
public class CucumberSpringContextConfiguration {

  @Before
  public void setup() {

  }
}