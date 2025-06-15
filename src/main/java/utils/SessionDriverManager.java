package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SessionDriverManager {

    private WebDriver driver;


    public WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("profile.default_content_settings.cookies", 2);

            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--no-sandbox");
            options.addArguments("--incognito");
            options.addArguments("disable-extensions");
            options.addArguments("start-maximized");
            options.addArguments("disable-cache");
            options.addArguments("disable-application-cache");
            options.addArguments("disable-offline-load-stale-cache");
            options.addArguments("disk-cache-size=0");
            options.addArguments("disable-infobars");
            options.addArguments("disable-browser-side-navigation");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--ignore-ssl-errors=yes"); // Added to handle insecure content
            options.addArguments("--ignore-certificate-errors");// Added to handle insecure content
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-backgrounding-occluded-windows");// To handle multiple windows
            options.addArguments("--deny-permission-prompts");

            driver = new ChromeDriver(options);// Initialize WebDriver
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
        }
        return driver;
    }

}