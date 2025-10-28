package tests.scenarios;

import base.BaseTest;
import dataproviders.DataProviders;
import org.testng.annotations.Test;

public class WishListTest extends BaseTest {

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


    @Test(dataProvider = "selectedItem", dataProviderClass = DataProviders.class)
    public void searchAndSelectProduct(String productName, String size, String color) {
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
    public void addProductToWishList() throws InterruptedException {
        wishListPage.addProductToWishList();

    }
}
