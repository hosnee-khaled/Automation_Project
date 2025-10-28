package utils.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetFirefoxDriver extends GetWebDriver{

    private GetFirefoxDriver(WebDriver driver) {
        super(driver);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--incognito");
            driver = new FirefoxDriver(options);
            return driver;
        }
        return driver;
    }
}
