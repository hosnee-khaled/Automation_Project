package tests.scenarios;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewsletterSubscriptionTest extends BaseTest {

//    @Test
//    public void singleLogin() {
//        loginPage.getLoginPage();
//        loginPage.verifyLoginTitleDisplayed();
//        loginPage.enterLoginData("hossne@gmail.com", "123456");
//        try {
//            loginPage.verifyLoginSuccessfully();
//        } catch (Exception e) {
//            registerPage.getRegisterPage();
//            registerPage.registerTest("Male", "Hosnee", "Khaled",
//                    "hossne@gmail.com", "NTI", "True",
//                    "123456", "123456");
//            registerPage.verifyRegisterSuccessfully();
//            registerPage.returnToRegisterPage();
//            singleLogin();
//        }
//    }

    @Test
    public void enterEmail() throws InterruptedException {
        newsletterSubscriptionPage.enterEmail("hossne@gmail.com");

    }

}
