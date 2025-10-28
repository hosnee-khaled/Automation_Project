package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import utils.BrowserFactory.GetChromeDriver;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected WishListPage wishListPage;
    protected CompareProductsPage compareProductsPage;
    protected NewsletterSubscriptionPage newsletterSubscriptionPage;
    private static int count = 1;


    @BeforeSuite
    public void setUp(){
        driver = GetChromeDriver.getDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        wishListPage = new WishListPage(driver);
        compareProductsPage = new CompareProductsPage(driver);
        newsletterSubscriptionPage = new NewsletterSubscriptionPage(driver);
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File directory = new File("./screenshots/");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "screenshots/" + result.getName() + count + ".png";
                FileHandler.copy(src, new File(screenshotPath));
                count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
