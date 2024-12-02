package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "./Feature",glue = "stepDefinition", plugin = {"pretty", "html:target/cucumber-reports.html"})

public class RunScript {

}
