package tests;

import base.BaseTest;
import dataproviders.DataProviders;
import org.testng.annotations.Test;
import utils.MongoDB.MongoDBTestListener;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void getLoginPage() {
        loginPage.getLoginPage();
        loginPage.verifyLoginTitleDisplayed();
    }

    @Test(priority = 2, dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void loginTest(String id, String gender, String firstName, String lastName,
                          String email, String companyName, String newLetters,
                          String password, String confirmPassword) {
        loginPage.getLoginPage();
        loginPage.verifyLoginTitleDisplayed();
        loginPage.enterLoginData(email, password);
        try {
            loginPage.verifyLoginSuccessfully();
            loginPage.returnToLoginPage();
        } catch (Exception e) {
            registerPage.getRegisterPage();
            registerPage.registerTest(gender, firstName, lastName, email, companyName, newLetters, password, confirmPassword);
            registerPage.verifyRegisterSuccessfully();
            registerPage.returnToRegisterPage();
            loginTest(id, gender, firstName, lastName, email, companyName, newLetters, password, confirmPassword);
        }
    }

    @Test(priority = 3, dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
    public void loginTestWithInvalidData(String id, String username, String password) {
        loginPage.getLoginPage();
        loginPage.verifyLoginTitleDisplayed();
        loginPage.enterLoginData(username, password);
        loginPage.verifyLoginTitleDisplayed();
    }

}
