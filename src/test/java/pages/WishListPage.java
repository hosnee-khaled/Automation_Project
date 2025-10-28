package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WishListPage {

    private WebDriver driver;
    WebDriverWait wait;
    private final By wishListQuantity = By.className("wishlist-qty");
    private final By wishListBtn = By.className("add-to-wishlist-button");
    private final By wishListIcon = By.className("ico-wishlist");

    public WishListPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProductToWishList() throws InterruptedException {
        String text = driver.findElement(wishListQuantity).getText();
        text = text.replace("(", "").replace(")", "");
        int count = Integer.parseInt(text);
        driver.findElement(wishListBtn).click();
        Thread.sleep(1000);
        text = driver.findElement(wishListQuantity).getText();
        text = text.replace("(", "").replace(")", "");
        int actual = Integer.parseInt(text);
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(wishListIcon)).click();
        Assert.assertEquals(actual, (count + 1));
    }
}
