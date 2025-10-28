package tests.scenarios;

import base.BaseTest;
import dataproviders.DataProviders;
import org.testng.annotations.Test;

public class CompareProductsTest extends BaseTest {

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
    public void navigateToCategory() {
        compareProductsPage.navigateToCategory();
        compareProductsPage.verifyCategoryTitleDisplayed();
    }

    @Test(dependsOnMethods = "navigateToCategory", dataProvider = "selectedItemsToCompare", dataProviderClass = DataProviders.class)
    public void selectItems(String category, String branch, String productName) throws InterruptedException {
        compareProductsPage.selectCategory(category);
        compareProductsPage.Branch(branch);
        compareProductsPage.addToCompareList(productName);
    }

    @Test(dependsOnMethods = "selectItems")
    public void verifyCompareListDisplayed() {
        compareProductsPage.verifyCompareListDisplayed();
    }

}
