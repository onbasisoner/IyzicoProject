package utils.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.helpers.PropertyManager;

public class EdgeWebDriver {

    PropertyManager propertyManager = new PropertyManager();
    WebDriver driver;
    String domain = propertyManager.getProperty("env.properties", "domain");
    String headlessCheck = propertyManager.getProperty("env.properties","headless");
    public WebDriver initializeAndGetEdgeDriver(){

        EdgeOptions options = new EdgeOptions();
        if (headlessCheck.equals("true")){
            options.addArguments("--headless");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--test-type");
        options.addArguments("--incognito");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-translate");
        options.addArguments("--always-authorize-plugins");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches",new String[]{"enable-automation", "disable-client-side-phishing-detection"});

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(options);
        driver.get(domain);

        return driver;
    }


}
