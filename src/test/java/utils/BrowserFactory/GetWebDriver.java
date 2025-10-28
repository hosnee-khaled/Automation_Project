package utils.BrowserFactory;

import org.openqa.selenium.WebDriver;

public abstract class GetWebDriver {
    protected static WebDriver driver;

    public GetWebDriver(WebDriver driver) {
        this.driver = driver;
    }
}
