# NopCommerce Automation Testing Project

## 1️⃣ Project Overview

This project is an **automated testing suite** for the [nopCommerce demo website](https://demo.nopcommerce.com/).
The automation covers key functionalities such as:

* Selecting random categories and subcategories
* Newsletter subscription using **database-driven data**
* Product comparison features

The project leverages **Selenium WebDriver** for browser automation and **TestNG** for test management.

---

## 2️⃣ Technologies & Tools

* **Java 24** – programming language
* **Selenium WebDriver 4.35** – browser automation
* **TestNG** – testing framework
* **Maven** – dependency and project management
* **MongoDB** – database for storing test data (emails)
* **BrowserFactory** – Chrome, Edge and Firefox browser  automation

---

## 3️⃣ Project Structure

```
NopCommerce-Automation/
│
├─ src/test/java/pages                 --> Page Object classes (e.g., NewsletterPage.java, CompareProductPage.java)
├─ src/test/java/base                  --> BaseTest class
├─ src/test/java/dataProviders         --> DataProviders class
├─ src/test/java/tests                 --> Test classes and scenarios classes (e.g., NewsletterTest.java)
├─ src/main/resources/                 --> Configuration files, database scripts
├─ src/test/java/utils/BrowserFactory  --> implement Factory and Singleton Design Pattern 
├─ pom.xml                             --> Maven dependencies
└─ README.md                           --> Project documentation
```

---

## 4️⃣ Database Setup (MongoDB)

### 4.1 Database Configuration

The project uses a **MongoDB database** named `final_project` with a collection `login_data`:

```json
// Example document in MongoDB
{
  "_id":{"$oid":"68e07c2f586e4df9b692d5a3"},
  "gender":"Male",
  "firstName":"hosnee",
  "lastName":"Khaled",
  "email":"hosdddnee@gmail.com",
  "companyName":"NTI",
  "newLetters":"True",
  "passowrd":"123456",
  "confirmPassword":"123456"
}
```

### 4.2 Connecting to MongoDB

Update your connection settings in `MongoDBConnection.java`:

```java
MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
MongoDatabase database = mongoClient.getDatabase("nopcommerce");
MongoCollection<Document> subscribersCollection = database.getCollection("subscribers");
```

---

## 5️⃣ Using DataProvider in Tests

We use **TestNG DataProvider** to fetch data from MongoDB:

```java
@DataProvider(name = "loginData")
    public Object[][] getValidLoginData() {
        MongoDBTestListener mongoDBTestListener = new MongoDBTestListener();
        return mongoDBTestListener.getTwoDArray("login_data");
    }
```


---

## 6️⃣ Running the Project

1. **Clone the repository:**

```bash
git clone https://github.com/yourusername/NopCommerce-Automation.git
```

2. **Open in IntelliJ IDEA** (or any Java IDE).
3. **Ensure MongoDB is running** and the database is populated with test data.
4. **Run tests via Maven:**

```bash
mvn test
```

5. **Notes:**

   * Tests run on Chrome browser.
   * `WebDriverWait` is used for dynamic elements.
   * Scroll or explicit waits are used if clicks are intercepted.

---

## 7️⃣ Key Functionalities

| Feature                   | Description                                                     |
| ------------------------- | --------------------------------------------------------------- |
| Random Category Selection | Randomly selects a category and subcategory and navigates to it |
| Newsletter Subscription   | Subscribes users using emails from MongoDB                      |
| Product Comparison        | Adds products to compare and verifies the comparison page       |
| Database-Driven Tests     | Feeds test data from MongoDB using TestNG DataProvider          |

---

## 8️⃣ Handling Dynamic Elements

* **WebDriverWait** for clickable elements:

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.elementToBeClickable(element)).click();
```

* **Scroll into view** for intercepted elements:

```java
((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
```

---

## 9️⃣ Known Issues / Notes

* Click interception may occur if elements overlap; use scroll or explicit waits.
* Ensure ChromeDriver version matches your browser.
* MongoDB must be running and correctly configured.
* Stale elements may appear after page updates; relocate elements after navigation.

---

## 🔟 Contact

* For questions: **[hosnee.kh@gmail.com](mailto:hosnee.kh@gmail.com)**
* Project Author: **Hosnee Khaled**

---

## 1️⃣1️⃣ Optional Enhancements

* Deploy MongoDB to a free online server for shared access.
* Fully implement **Page Object Model (POM)** for better maintainability.
* Capture **screenshots on test failure** using Selenium `TakesScreenshot`.
* Integrate **GitHub Actions** for automated test execution.

---


