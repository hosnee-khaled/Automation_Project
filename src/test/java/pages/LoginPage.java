package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    private WebDriver driver;
    private final By loginButton = By.className("ico-login");
    private final By loginTitle = By.xpath("//h1[text()='Welcome, Please Sign In!']");
    private final By emailElement = By.className("email");
    private final By passwordElement = By.className("password");
    private final By iconAccount = By.className("ico-account");
    private final By logoutButton = By.className("ico-logout");
    private final By errorMessage = By.className("message-error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getLoginPage() {
        driver.findElement(loginButton).click();
    }

    public void verifyLoginTitleDisplayed() {
        Assert.assertTrue(driver.findElement(loginTitle).isDisplayed());
    }

    public void enterLoginData(String email, String password) {
        driver.findElement(emailElement).sendKeys(email);
        driver.findElement(passwordElement).sendKeys(password, Keys.ENTER);
    }

    public void verifyLoginSuccessfully() {
        Assert.assertTrue(driver.findElement(iconAccount).isDisplayed());
    }

    public void returnToLoginPage() {
        driver.findElement(logoutButton).click();
        driver.findElement(loginButton).click();
    }

    public void verifyLoginFailed() {
        Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
    }
}
