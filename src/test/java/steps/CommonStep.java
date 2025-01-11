package steps;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import pages.CommonPage;
import report.ScenarioTestResult;
import utils.helpers.Helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class CommonStep {

    CommonPage page;

    public static String ssPath;

    public static int currentStepDefIndex;

    public static List<ScenarioTestResult.Step> steps;

    public void getSS() {
        ssPath = Helper.getSS().getAbsolutePath();
    }

    private void getSteps(Scenario scenario) throws Exception {
        String testScenario = scenario.getName().toString();
        Scenario scenarioObj = scenario;
        Field f1 = scenario.getClass().getDeclaredField("delegate");
        f1.setAccessible(true);
        TestCaseState r = (TestCaseState) f1.get(scenario);

        Field f2 = r.getClass().getDeclaredField("testCase");
        f2.setAccessible(true);
        TestCase r2 = (TestCase) f2.get(r);

        List<PickleStepTestStep> stepDefs = r2.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());

        steps = new ArrayList<>();
        for (PickleStepTestStep step : stepDefs) {
            steps.add(new ScenarioTestResult.Step(steps.size() + 1, step.getStep().getText()));
        }
    }

    @Before
    public void setUp(Scenario scenario) throws Exception {
        page = new CommonPage();
        currentStepDefIndex = 0;

        getSteps(scenario);
    }

    @BeforeStep
    public void beforeStep() {
    }

    @AfterStep
    public void afterStep() {

        currentStepDefIndex += 1;
    }

    @After
    public void tearDown() {
        getSS();
        page.tearDown();
    }

    @Then("Click {string} element")
    public void click(String key) {
        page.click(key);
    }

    @And("Fill {string} field with {string}")
    public void fillByToWith(String key, String text) {
        page.fill(key, text);
    }

    @And("Get text from {string} and paste into this field {string}")
    public void fillTextboxWithString(String text, String textbox){
        page.fillTextBoxWithSpecificText(text,textbox);
    }


    @When("Check {string} field existence on page")
    public void checkTextOnPage(String key) {
        page.checkWithText(key);
    }

    @Then("Clear {string} input field")
    public void clearText(String key) {
        page.clearText(key);
    }

    @And("Wait for given seconds {int}")
    public void waitForGivenSeconds(int seconds) {
        page.waitFor(seconds);
    }

    @Then("Wait for the {string} element to display on screen")
    public void waitForElementToDisplay(String key) {
        page.waitForTheElementDisplay(key);
    }

    @Then("Wait for the {string} element to be clickable")
    public void waitForElementToClickable(String key) {
        page.waitForTheElementClickable(key);
    }

    @When("Check page url contains {string}")
    public void checkPageUrlContains(String url) {
        page.verifyUrl(url);
    }

    @When("Check page title contains {string}")
    public void checkPageTitleContains(String title) {
        page.verifyPageTitle(title);
    }

    @And("Wait until {string} element is visible")
    public void waitUntilVisible(String key) {
        page.waitUntilElementVisible(key);
    }

    @And("Click Enter")
    public void clickEnter() {
        page.clickEnter();
    }

    @Then("Refresh page")
    public void refreshPage() {
        page.refreshPage();
    }

}
