package utils.helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.drivers.WebDrivers;
import utils.helpers.elementHelper.ElementMap;
import utils.helpers.elementHelper.ElementResponse;
import utils.helpers.elementHelper.Elements;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.Duration;

public class Helper {

    public WebDriver webDriver = null;


    WebDrivers webDrivers = new WebDrivers();


    //Start webdriver
    public Helper() {
        if (webDriver == null) {
            webDriver = webDrivers.createAndGetDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        }
    }

    // Waits until element is visible
    public WebElement waitUntilElementIsVisible(WebElement element, long startTime) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebDriverWait subWait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        if ((System.currentTimeMillis() - startTime) > 10000) {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return null;
        }
        try {
            subWait.until(ExpectedConditions.visibilityOf(element));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return element;
        } catch (StaleElementReferenceException | TimeoutException e) {
            return waitUntilElementIsVisible(element, startTime);
        }
    }

    // Waits until element is clickable
    public WebElement waitUntilElementIsClickable(WebElement element, long startTime) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebDriverWait subWait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        if ((System.currentTimeMillis() - startTime) > 15000) {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return null;
        }
        try {
            subWait.until(ExpectedConditions.elementToBeClickable(element));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return element;
        } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
            System.out.println(e.getMessage());
            return waitUntilElementIsClickable(element, startTime);
        }
    }

    // Center element with using JavaScript
    public WebElement centerElement(WebElement element) {

        String scrollScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) webDriver).executeScript(scrollScript, element);

        waitForGivenTime(1);

        return element;
    }

    // Waits for given time
    public void waitForGivenTime(double duration) {
        try {
            Thread.sleep((long) (duration * 1000L));
        } catch (InterruptedException exception) {
            Assert.fail(exception.getLocalizedMessage());
        }
    }

    //Get element with given key
    public By getBy(String key) {
        ElementResponse elementInfo = ElementMap.INSTANCE.findElementInfoByKey(key);
        return Elements.getElementInfoToBy(elementInfo);
    }

    //Get screenshot
    public static File getSS() {
        TakesScreenshot scrShot = ((TakesScreenshot) WebDrivers.getDriver());
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        File img = new File(System.getProperty("user.dir") + "/target/ss/web_" + timeStamp + ".jpg");
        try {
            FileUtils.copyFile(srcFile, img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

}
