package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.Runtime.Timestamp;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    // public final String lastGeneratedUsername = null;

    public static final String lastGeneratedUserName = null;

    // call remotewebdriver as driver
    RemoteWebDriver driver;

    // // url of register page
    // String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    // page factory
    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailTextBox;

    @FindBy(xpath = "//*[@placeholder='Type to create account password']")
    private WebElement createPassw;

    @FindBy(id = "//*[@placeholder='Retype Password to Confirm']")
    private WebElement retypePassw;

    @FindBy(xpath = "//*[text()='Register Now']")
    private WebElement registerNowButton;


    public RegisterPage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
    }

    // Get time stamp for generating a unique username
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    // Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

    public void navigateToHomePage() {
        // navigate to home page
        // driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        SeleniumWrapper.navigatetoURL(driver, "https://qtripdynamic-qa-frontend.vercel.app/");

    }

    public Boolean registerUser(String username, String password, Boolean makeUsernameDynamic)
            throws InterruptedException {

        // public Boolean registerUser(String username, String password, boolean
        // generateRandomUsername)

        // if (usernameDynamic)

        // username = username + String.valueOf(timeStamp.getTime());

        // String testPassword = password;

        // WebElement usernameTextBox = driver.findElement(By.id("username"));
        // WebElement passwordTextBox = driver.findElement(By.id("password"));
        // WebElement confirmPasswordTextBox = driver.findElement(By.id("confirmPassword"));
        // WebElement registerNowButton = driver.findElement(By.className("button"));

        // WebElement emailAdd =
        // driver.findElement(By.xpath("//input[@placeholder='name@example.com']"));
        // emailAdd.sendKeys("testUser@gmail.com");

        WebElement emailAdd = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//input[@placeholder='name@example.com']"), 3);
        SeleniumWrapper.sendKeys(emailAdd, "testUser@gmail.com");

        // WebElement typeTo = driver
        // .findElement(By.xpath("//input[@placeholder='Type to create account password']"));
        // typeTo.sendKeys("abc@123");

        WebElement typeTo = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//input[@placeholder='Type to create account password']"), 3);
        SeleniumWrapper.sendKeys(typeTo, "abc@123");

        // WebElement confirm =
        // driver.findElement(By.xpath("//input[@placeholder='Retype Password to Confirm']"));
        // confirm.sendKeys("abc@123");

        WebElement confirm = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//input[@placeholder='Retype Password to Confirm']"), 3);
        SeleniumWrapper.sendKeys(confirm, "abc@123");

        // WebElement regNowBtn = driver.findElement(By.xpath("//button[text()='Register Now']"));
        // regNowBtn.click();

        WebElement regNowBtn = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("/button[text()='Register Now']"), 3);
        SeleniumWrapper.click(regNowBtn, driver);

        if (makeUsernameDynamic == true)
            username = username + UUID.randomUUID().toString();

        // try {
        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.or(
        // ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/")));
        // } catch (TimeoutException e) {
        // return false;
        // }

        // emailTextBox.sendKeys(username);
        // createPassw.sendKeys(password);
        // retypePassw.sendKeys(password);
        // registerNowButton.click();
        // Thread.sleep(3000);

        // this.lastGeneratedUsername = username;

        // return this.driver.getCurrentUrl().endsWith("/login");
        return true;

    }


    public void registerNewUser(String userName, String password) throws InterruptedException {
        // reg. starts from home page
        navigateToHomePage();
        WebElement register =
                SeleniumWrapper.findElementWithRetry(driver, By.xpath("//a[text()='Register']"), 3);
        SeleniumWrapper.click(register, driver);

        // WebElement register = driver.findElement(By.xpath("//a[text()='Register']"));
        // register.click();

        WebElement emailAdd = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//input[@placeholder='name@example.com']"), 3);
        SeleniumWrapper.sendKeys(emailAdd, "TESTUSER@gmail.com");

        // WebElement emailAdd =
        // driver.findElement(By.xpath("//input[@placeholder='name@example.com']"));
        // emailAdd.sendKeys("TESTUSER@gmail.com");

        WebElement typeTo = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//input[@placeholder='Type to create account password']"), 3);
        SeleniumWrapper.sendKeys(typeTo, "1234567");

        // WebElement typeTo = driver
        // .findElement(By.xpath("//input[@placeholder='Type to create account password']"));
        // typeTo.sendKeys("1234567");

        WebElement confirm = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//input[@placeholder='Retype Password to Confirm']"), 3);
        SeleniumWrapper.sendKeys(confirm, "1234567");

        // WebElement confirm =
        // driver.findElement(By.xpath("//input[@placeholder='Retype Password to Confirm']"));
        // confirm.sendKeys("1234567");

        WebElement regNowBtn = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//button[text()='Register Now']"), 3);
        SeleniumWrapper.click(regNowBtn, driver);

        // WebElement regNowBtn = driver.findElement(By.xpath("//button[text()='Register Now']"));
        // regNowBtn.click();

        // registerNewUser(userName, password);
    }

    public void navigateToRegisterPage() {
        // if (!driver.getCurrentUrl().equals(this.lastGeneratedUserName)) {
        // driver.get(this.lastGeneratedUserName);
        // driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
        SeleniumWrapper.navigatetoURL(driver,
                "https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
    }
}

// public Boolean registerUser(String Username, String Password, Boolean makeUsernameDynamic)
// throws InterruptedException {
// // Find the Username Text Box
// WebElement username_txt_box = this.driver.findElement(By.id("username"));

// // Get time stamp for generating a unique username
// Timestamp timestamp = new Timestamp(System.currentTimeMillis());

// String test_data_username;
// if (makeUsernameDynamic)
// // Concatenate the timestamp to string to form unique timestamp
// test_data_username = Username + "_" + String.valueOf(timestamp.getTime());
// else
// test_data_username = Username;

// // Type the generated username in the username field
// this.clearTextbox(username_txt_box);
// username_txt_box.sendKeys(test_data_username);

// // Find the password Text Box
// WebElement password_txt_box = this.driver.findElement(By.id("password"));
// String test_data_password = Password;

// // Enter the Password value
// this.clearTextbox(password_txt_box);
// password_txt_box.sendKeys(test_data_password);

// WebElement confirm_password_txt_box = this.driver.findElement(By.id("confirmPassword"));

// // Enter the Confirm Password Value
// this.clearTextbox(confirm_password_txt_box);
// confirm_password_txt_box.sendKeys(test_data_password);

// // Find the register now button
// WebElement register_now_button = this.driver.findElement(By.className("button"));

// // Click the register now button
// register_now_button.click();

// try {
// WebDriverWait wait = new WebDriverWait(driver, 30);
// wait.until(ExpectedConditions.or(
// ExpectedConditions.urlToBe("https://crio-qkart-frontend-qa.vercel.app/login")));
// } catch (TimeoutException e) {
// return false;
// }

// this.lastGeneratedUsername = test_data_username;

// return this.driver.getCurrentUrl().endsWith("/login");
// }
// }
