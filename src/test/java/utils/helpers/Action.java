package utils.helpers;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class Action extends Helper{

    Actions actions;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

    public Action() {
        actions = new Actions(webDriver);
    }

    // Clicks given element
    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        centerElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    //Find given element
    public WebElement findElement(String key) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 20000) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(getBy(key)));
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    // Clear textbox and fill with given text
    public void clearAndFillInput(String key, String text) {
        try {
            centerElement(waitUntilElementIsVisible(findElement(key),System.currentTimeMillis())).clear();
            centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).sendKeys(text);
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    //This method substring of a specific text and set this text to TextBox
    //This is specific to this project, code is always same but i try to get like that
    public void fillStringOfaTextIntoTextBox(String text,String TextBox){
        String substr = findElement(text).getText().substring(27,33);
        try {
            findElement(TextBox).sendKeys(substr);
        }catch (ElementNotFoundException e){
            Assert.fail(e.getMessage());
        }
    }

    // Clear written text on element
    public void clearFilledInput(String key) {
        try {
            findElement(key).clear();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // Check visibility of elements on the page
    public void checkWithTextOnPage(String key) {
        try {
            waitForVisibilityOfElement(findElement(key));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Wait for specific given time as seconds
    public void waitFor(double duration) {
        waitForGivenTime(duration);
    }

    // Wait for visibility for element explicitly
    public void waitForVisibilityOfElement(WebElement key) {
        try {
            wait.until(ExpectedConditions.visibilityOf(key));
        } catch (Exception e) {
            System.out.println("Given element " + key + " did not displayed in 30 seconds!!");
        }
    }

    // Wait for element to be clickable explicitly
    public void waitForElementToClick(WebElement key) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(key));
        } catch (Exception e) {
            System.out.println("Given element " + key + " did not clickable in 30 seconds!!");
        }
    }

    // Verifies current url with given url
    public void verifyUrl(String url) {
        Assert.assertTrue(webDriver.getCurrentUrl().contains(url));
    }

    // Verifies the page title
    public void verifyPageTitle(String pageTitle) {
        Assert.assertTrue(webDriver.getTitle().contains(pageTitle));
    }

    // Refresh Page
    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    // Teardown the driver
    public void tearDown() {
        webDriver.quit();
    }

    //Click Enter Button
    public void clickEnterButton(){
        actions.sendKeys(Keys.ENTER).build().perform();
    }
}
