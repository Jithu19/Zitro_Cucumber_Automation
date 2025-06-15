package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.zitro.stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/Html.html", "json:target/cucumber-reports/CucumberJson.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@SmokeTest"
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}