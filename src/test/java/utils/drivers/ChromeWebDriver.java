package utils.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.helpers.PropertyManager;


public class ChromeWebDriver {


    PropertyManager propertyManager = new PropertyManager();
    WebDriver driver;
    String domain = propertyManager.getProperty("env.properties", "domain");
    String headlessCheck = propertyManager.getProperty("env.properties","headless");


    public WebDriver initializeAndGetChromeDriver() {

        ChromeOptions options = new ChromeOptions();
        if (headlessCheck.equals("true")){
            options.addArguments("--headless");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-translate");
        options.addArguments("--always-authorize-plugins");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--incognito");

        options.setExperimentalOption("excludeSwitches",new String[]{"enable-automation", "disable-client-side-phishing-detection"});

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        driver.get(domain);

        return driver;
    }
}
