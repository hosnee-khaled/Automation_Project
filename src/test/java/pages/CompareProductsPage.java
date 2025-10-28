package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class CompareProductsPage {

    private WebDriver driver;
    private final By category = By.xpath("//a[normalize-space(text())='Computers']");
    private final By categoryTitle = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/strong");
    private final By getCompareProductList = By.xpath("//a[normalize-space(text())='Compare products list']");
    private final By comparePageTitle = By.xpath("//h1[normalize-space(text())='Compare products']");


    public CompareProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCategory() {
        driver.findElement(category).click();
    }

    public void verifyCategoryTitleDisplayed() {
        Assert.assertTrue(driver.findElement(categoryTitle).isDisplayed());
    }

    public void selectCategory(String category) {
        driver.findElement(By.xpath("//a[normalize-space(text())='" + category + "']")).click();
    }

    public void Branch(String branch) throws InterruptedException {
        try {
            driver.findElement(
                    By.xpath("//ul[@class='sublist']/li[contains(@class,'inactive')]/a[normalize-space(text())='" + branch + "']")
            ).click();
        } catch (Exception _) {}
    }

    public void addToCompareList(String productName) throws InterruptedException {
        Thread.sleep(4000);
        WebElement compareButton = driver.findElement(
                By.xpath("//h2[@class='product-title']/a[normalize-space(text())='" + productName + "']/parent::h2/following::div[@class='buttons']//button[contains(@class,'add-to-compare-list-button')]")
        );
        compareButton.click();
    }

    public void verifyCompareListDisplayed() {
        driver.findElement(getCompareProductList).click();
        Assert.assertTrue(driver.findElement(comparePageTitle).isDisplayed());
    }
}
