package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NewsletterSubscriptionPage {

    private WebDriver driver;

    public NewsletterSubscriptionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        driver.findElement(By.id("newsletter-email")).sendKeys(email, Keys.ENTER);
        Thread.sleep(1000);
        String result = driver.findElement(By.className("newsletter-result")).getText();
        Assert.assertEquals(result, "Thank you for signing up! A verification email has been sent. We appreciate your interest.");
    }
}
