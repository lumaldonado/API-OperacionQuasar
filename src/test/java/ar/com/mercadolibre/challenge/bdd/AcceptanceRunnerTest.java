package ar.com.mercadolibre.challenge.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "json:target/cucumber.json"},
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    features = "classpath:features"
)
public class AcceptanceRunnerTest {
}
