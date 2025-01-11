package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report.html","pretty"},
        glue = "steps",
        features = "src/test/resources/features/task.feature",
        publish = true,
        tags = "@regression"
)

public class TestRunner {


}
