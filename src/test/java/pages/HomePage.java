package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    WebDriverWait wait;
    private final By searchBox = By.className("search-box-text");
    private final By print = By.cssSelector("li[data-attr-value='19']");
    private final By overview = By.className("overview");
    private final By cartButton = By.className("ico-cart");
    private final By agreeTerms = By.id("termsofservice");
    private final By checkoutButton = By.id("checkout");
    private final By shippingMethodBtn = By.className("shipping-method-next-step-button");
    private final By paymentMethodBtn = By.className("payment-method-next-step-button");
    private final By confirmMethodBtn = By.className("confirm-order-next-step-button");
    private final By paymentInfoBtn = By.className("payment-info-next-step-button");
    private final By orderTitle = By.xpath("//div[@class='title']/strong");
    private final By orderNumber = By.className("order-number");
    private final By countryId = By.xpath("//select[contains(@name,'CountryId')]");
    private final By stateProvinceId = By.xpath("//select[contains(@name,'StateProvinceId')]");
    private final By city = By.id("BillingNewAddress_City");
    private final By firstAddress = By.id("BillingNewAddress_Address1");
    private final By secondAddress = By.id("BillingNewAddress_Address2");
    private final By postalCode = By.id("BillingNewAddress_ZipPostalCode");
    private final By phoneNumber = By.id("BillingNewAddress_PhoneNumber");
    private final By faxNumber = By.id("BillingNewAddress_FaxNumber");
    private final By shoppingCart = By.xpath("//h1[normalize-space(text())='Shopping cart']");
    private final By addToCartBtn = By.xpath("//button[starts-with(@id,'add-to-cart-button')]");
    private final By cartQuantity = By.className("cart-qty");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterSearchText(String searchText) {
        driver.findElement(searchBox).sendKeys(searchText, Keys.ENTER);
    }

    private Select getSelector(String labelText) {
        WebElement label = driver.findElement(By.xpath("//dt[label[normalize-space(text())='" + labelText + "']]"));
        WebElement dropdown = label.findElement(By.xpath("following-sibling::dd/select"));
        return new Select(dropdown);
    }

    public void selectProduct(String productName) {
        WebElement addToCartBtn = driver.findElement(
                By.xpath("//h2[@class='product-title']/a[normalize-space(text())='" + productName + "']")
        );
        addToCartBtn.click();

    }

    public void selectSize(String size) throws InterruptedException {
        Thread.sleep(3000);
        Select select = getSelector("Size");
        select.selectByVisibleText(size);
    }

    public void selectColor(String color) {
        Select select = getSelector("Color");
        select.selectByVisibleText(color);
    }

    public void addToCartAndVerify() throws InterruptedException {
        String text = driver.findElement(cartQuantity).getText();
        text = text.replace("(", "").replace(")", "");
        int count = Integer.parseInt(text);
        driver.findElement(this.addToCartBtn).click();
        Thread.sleep(1000);
        text = driver.findElement(cartQuantity).getText();
        text = text.replace("(", "").replace(")", "");
        int actual = Integer.parseInt(text);
        Assert.assertEquals(actual, (count + 1));
    }

    public void selectPrint() {
        driver.findElement(print).click();
        driver.findElement(overview).click();
    }

    public void goToCart() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(cartButton).click();
    }

    public void verifyCartPageDisplayed() {
        Assert.assertTrue(driver.findElement(shoppingCart).isDisplayed());
    }

    public void selectAgreeTerms() {
        driver.findElement(agreeTerms).click();
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }

    public void addInvalidCheckoutInfo(String firstName, String lastName, String email, String companyName,
                                String country, String state, String city, String firstAddress,
                                String secondAddress, String postalCode, String phoneNumber, String faxNumber) {
        try{
            selectCountry(country);
            selectState(state);
            driver.findElement(this.city).sendKeys(city);
            driver.findElement(this.firstAddress).sendKeys(firstAddress);
            driver.findElement(this.secondAddress).sendKeys(secondAddress);
            driver.findElement(this.postalCode).sendKeys(postalCode);
            driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
            driver.findElement(this.faxNumber).sendKeys(faxNumber, Keys.ENTER);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).perform();
            driver.navigate().refresh();
        } catch (Exception _) {}
    }

    public void addCheckoutInfo(String firstName, String lastName, String email, String companyName,
                                String country, String state, String city, String firstAddress,
                                String secondAddress, String postalCode, String phoneNumber, String faxNumber) throws InterruptedException {
        try {
            Thread.sleep(3000);
            selectCountry(country);
            selectState(state);
            driver.findElement(this.city).sendKeys(city);
            driver.findElement(this.firstAddress).sendKeys(firstAddress);
            driver.findElement(this.secondAddress).sendKeys(secondAddress);
            driver.findElement(this.postalCode).sendKeys(postalCode);
            driver.findElement(this.phoneNumber).sendKeys("0" + phoneNumber);
            driver.findElement(this.faxNumber).sendKeys(faxNumber, Keys.ENTER);
        } catch (Exception e) {
            Thread.sleep(3000);
            driver.findElement(By.className("new-address-next-step-button")).click();
        }

    }

    public void selectCountry(String country) {
        WebElement countryDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(countryId));
        Select select = new Select(countryDropdown);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(countryId, country));
        select.selectByVisibleText(country);
    }

    public void selectState(String state) {
        WebElement stateDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(stateProvinceId));
        Select select = new Select(stateDropdown);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(stateProvinceId, state));
        select.selectByVisibleText(state);
    }

    public void continueToCheckout() {
        try {
            driver.findElement(By.className("new-address-next-step-button")).click();
            wait.until(ExpectedConditions.elementToBeClickable(shippingMethodBtn)).click();
            wait.until(ExpectedConditions.elementToBeClickable(paymentMethodBtn)).click();
            wait.until(ExpectedConditions.elementToBeClickable(paymentInfoBtn)).click();
            wait.until(ExpectedConditions.elementToBeClickable(confirmMethodBtn)).click();
        } catch (Exception _) {
            Assert.fail("Error while clicking on continue-to-checkout");
        }

    }

    public void verifyOrderConfirmation() {
        Boolean isOrderSuccess = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(orderTitle,
                        "Your order has been successfully processed!"
                )
        );
        Assert.assertTrue(isOrderSuccess, "Order was not completed successfully!");
        String orderNumber = driver.findElement(this.orderNumber).getText();
        System.out.println(orderNumber);
    }

}
