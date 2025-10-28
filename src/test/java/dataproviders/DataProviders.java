package dataproviders;

import org.testng.annotations.DataProvider;
import utils.FileManager.ExcelFileManager;
import utils.MongoDB.MongoDBTestListener;

public class DataProviders {

    @DataProvider(name = "invalidRegisterData")
    public Object[][] getInvalidRegisterData() {
        ExcelFileManager excelFileManager = new ExcelFileManager("src/test/resources/RegisterData.xlsx", "InvalidRegisterData");
        return excelFileManager.addDataToTwoDArray();
    }

    @DataProvider(name = "validRegisterData")
    public Object[][] getValidRegisterData() {
        ExcelFileManager excelFileManager = new ExcelFileManager("src/test/resources/RegisterData.xlsx", "ValidRegisterData");
        return excelFileManager.addDataToTwoDArray();
    }

    @DataProvider(name = "loginData")
    public Object[][] getValidLoginData() {
        MongoDBTestListener mongoDBTestListener = new MongoDBTestListener();
        return mongoDBTestListener.getTwoDArray("login_data");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        MongoDBTestListener mongoDBTestListener = new MongoDBTestListener();
        return mongoDBTestListener.getTwoDArray("invalid_login_data");
    }

    @DataProvider(name = "selectedItem")
    public Object[][] selectedItem() {
        return new Object[][]{
                {"Nike Floral Roshe Customized Running Shoes", "10", "White/Black"}};
//                {"adidas Consortium Campus 80s Running Shoes", "10", "Blue"}};
//                {"Asus Laptop", "", ""}};
    }

    @DataProvider(name = "validCheckoutData")
    public Object[][] getValidCheckoutData() {
        ExcelFileManager excelFileManager = new ExcelFileManager("src/test/resources/CheckoutInfo.xlsx", "ValidCheckoutData");
        return excelFileManager.addDataToTwoDArray();
    }

    @DataProvider(name = "invalidCheckoutData")
    public Object[][] getInvalidCheckoutData() {
        ExcelFileManager excelFileManager = new ExcelFileManager("src/test/resources/CheckoutInfo.xlsx", "InvalidCheckoutData");
        return excelFileManager.addDataToTwoDArray();
    }

    @DataProvider(name = "selectedItemsToCompare", parallel = false)
    public Object[][] selectedItemsToCompare() {
        return new Object[][]{
                {"Apparel", "Shoes", "adidas Consortium Campus 80s Running Shoes"},
                {"Apparel", "Shoes", "Nike Floral Roshe Customized Running Shoes"},
                {"Books", "", "Fahrenheit 451 by Ray Bradbury"},
                {"Books", "", "First Prize Pies"}};
    }

}
