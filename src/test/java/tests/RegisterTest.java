package tests;

import base.BaseTest;
import dataproviders.DataProviders;
import org.testng.annotations.Test;


public class RegisterTest extends BaseTest {

    @Test
    public void getRegisterTest() {
        registerPage.getRegisterPage();
        registerPage.verifyElementDisplayed();
    }

    @Test
    public void submitButtonTest() {
        registerPage.getRegisterPage();
        registerPage.submitButtonTest();
        registerPage.verifyElementDisplayed();
    }

    @Test(dataProvider = "invalidRegisterData", dataProviderClass = DataProviders.class)
    public void registerWithInvalidDataTest(String gender, String firstName, String lastName,
                                            String email, String companyName, String newLetters,
                                            String password, String confirmPassword) {
        registerPage.getRegisterPage();
        registerPage.registerTest(gender, firstName, lastName, email,
                companyName, newLetters, password, confirmPassword);
        try {
            registerPage.verifyErrorElementDisplayed();
        } catch (Exception e) {
            registerPage.returnToRegisterPage();
            registerPage.registerSuccessfullyWithInvalidData();
        }
    }

    @Test(dataProvider = "validRegisterData", dataProviderClass = DataProviders.class)
    public void registerWithValidDataTest(String gender, String firstName, String lastName,
                                          String email, String companyName, String newLetters,
                                          String password, String confirmPassword) {
        registerPage.getRegisterPage();
        registerPage.registerTest(gender, firstName, lastName, email,
                companyName, newLetters, password, confirmPassword);
        registerPage.verifyRegisterSuccessfully();
        registerPage.returnToRegisterPage();
    }
}
