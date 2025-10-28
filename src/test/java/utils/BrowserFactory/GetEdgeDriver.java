package utils.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class GetEdgeDriver extends GetWebDriver{

    private GetEdgeDriver(WebDriver driver) {
        super(driver);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--incognito");
            driver = new EdgeDriver(options);
            return driver;
        }
        return driver;
    }

}
