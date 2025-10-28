package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Objects;

public class RegisterPage {
    private WebDriver driver;
    private final By registerButton = By.className("ico-register");
    private final By logoutButton = By.className("ico-logout");
    private final By registerTitle = By.xpath("//h1[text()='Register']");
    private final By firstName = By.id("FirstName");
    private final By lastName = By.id("LastName");
    private final By email = By.id("Email");
    private final By companyName = By.id("Company");
    private final By newsletter = By.id("Newsletter");
    private final By password = By.id("Password");
    private final By confirmPassword = By.id("ConfirmPassword");
    private final By submitButton = By.id("register-button");
    private final By error = By.xpath("//*[contains(@class, 'error')]");
    private final By registerResult = By.xpath("//div[text()='Your registration completed']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getRegisterPage() {
        driver.findElement(registerButton).click();
    }

    public void verifyElementDisplayed() {
        Assert.assertTrue(driver.findElement(registerTitle).isDisplayed());
    }

    public WebDriver registerTest(String gender, String firstName, String lastName,
                             String email, String companyName, String newLetters,
                             String password, String confirmPassword) {
        if (gender != null && !gender.trim().isEmpty()) {
            driver.findElement(By.xpath("//*[text()='" + gender + "']")).click();
        }
        driver.findElement(this.firstName).sendKeys(firstName);
        driver.findElement(this.lastName).sendKeys(lastName);
        driver.findElement(this.email).sendKeys(email);
        driver.findElement(this.companyName).sendKeys(companyName);
        if (Objects.equals(newLetters, "FALSE")){
            driver.findElement(this.newsletter).click();
        }
        driver.findElement(this.newsletter).sendKeys(newLetters);
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(this.confirmPassword).sendKeys(confirmPassword);
        driver.findElement(submitButton).click();
        return driver;
    }

    public void submitButtonTest() {
        driver.findElement(submitButton).click();
    }

    public void verifyErrorElementDisplayed() {
        Assert.assertTrue(driver.findElement(error).isDisplayed());
    }

    public void returnToRegisterPage() {
        driver.findElement(logoutButton).click();
        driver.findElement(registerButton).click();
    }

    public void registerSuccessfullyWithInvalidData() {
        Assert.fail("Register has been successfully with wrong data");
    }

    public void verifyRegisterSuccessfully() {
        Assert.assertTrue(driver.findElement(registerResult).isDisplayed());
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}
