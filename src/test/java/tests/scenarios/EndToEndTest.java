package tests.scenarios;

import base.BaseTest;
import dataproviders.DataProviders;
import org.testng.annotations.Test;


public class EndToEndTest extends BaseTest {


    @Test
    public void singleLogin() {
        loginPage.getLoginPage();
        loginPage.verifyLoginTitleDisplayed();
        loginPage.enterLoginData("hossne@gmail.com", "123456");
        try {
            loginPage.verifyLoginSuccessfully();
        } catch (Exception e) {
            registerPage.getRegisterPage();
            registerPage.registerTest("Male", "Hosnee", "Khaled",
                    "hossne@gmail.com", "NTI", "True",
                    "123456", "123456");
            registerPage.verifyRegisterSuccessfully();
            registerPage.returnToRegisterPage();
            singleLogin();
        }
    }

    @Test(dependsOnMethods = "singleLogin", dataProvider = "selectedItem", dataProviderClass = DataProviders.class)
    public void searchAndSelectProduct(String productName, String size, String color) throws InterruptedException {
        homePage.enterSearchText(productName);
        homePage.selectProduct(productName);
        try {
            homePage.selectSize(size);
        } catch (Exception _) {
        }
        try {
            homePage.selectColor(color);
        } catch (Exception _) {
        }
        try {
            homePage.selectPrint();
        } catch (Exception _) {
        }
    }

    @Test(dependsOnMethods = "searchAndSelectProduct")
    public void addToCart() throws InterruptedException {
        homePage.addToCartAndVerify();
    }

    @Test(dependsOnMethods = "addToCart")
    public void goToCart() throws InterruptedException {
        homePage.goToCart();
        homePage.verifyCartPageDisplayed();
    }

    @Test(dependsOnMethods = "goToCart")
    public void checkout() {
        homePage.selectAgreeTerms();
        homePage.checkout();
    }

    @Test(dependsOnMethods = "checkout", dataProvider = "invalidCheckoutData", dataProviderClass = DataProviders.class)
    public void invalidCheckoutTest(String firstName, String lastName, String email, String companyName,
                             String country, String state, String city, String firstAddress,
                             String secondAddress, String postalCode, String phoneNumber, String faxNumber) {
        homePage.addInvalidCheckoutInfo(firstName, lastName, email, companyName,
                country, state, city, firstAddress, secondAddress, postalCode, phoneNumber, faxNumber);
    }

    @Test(dependsOnMethods = "invalidCheckoutTest", dataProvider = "validCheckoutData", dataProviderClass = DataProviders.class)
    public void checkoutTest(String firstName, String lastName, String email, String companyName,
                             String country, String state, String city, String firstAddress,
                             String secondAddress, String postalCode, String phoneNumber, String faxNumber) throws InterruptedException {
        homePage.addCheckoutInfo(firstName, lastName, email, companyName,
                country, state, city, firstAddress, secondAddress, postalCode, phoneNumber, faxNumber);
    }

    @Test(dependsOnMethods = "checkoutTest")
    public void continueToConfirm() {
        homePage.continueToCheckout();
        homePage.verifyOrderConfirmation();
    }
}
