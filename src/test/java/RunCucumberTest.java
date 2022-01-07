import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty", "json:target/cucumber.json", "html:target/cucumber"}, dryRun = true,
                features={"src/test/resources"})
public class RunCucumberTest{
}
