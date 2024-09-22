package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void navigateToLoginPage() {
        // if (!this.driver.getCurrentUrl().equals(this.url)) {
        // this.driver.get(this.url);
        // driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/login/");
        SeleniumWrapper.navigatetoURL(driver,
                "https://qtripdynamic-qa-frontend.vercel.app/pages/login/");
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailTextBox;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[text()='Login to QTrip']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[text()='Logout to QTrip']")
    private WebElement logoutButton;

    // private final static String LOGIN_PAGE_ENDPOINT = "login";

    // public boolean checkLoginPageNavigation() {
    // return driver.getCurrentUrl().contains(LOGIN_PAGE_ENDPOINT);
    // }

    public Boolean performLogin(String username, String password) throws InterruptedException {

        // emailTextBox.sendKeys("testUser123@gmail.com");
        // passwordTextBox.sendKeys("abc@123");
        // loginButton.click();
        SeleniumWrapper.sendKeys(emailTextBox, "testUser123@gmail.com");
        SeleniumWrapper.sendKeys(passwordTextBox, "abc@123");
        SeleniumWrapper.click(loginButton, driver);
        Thread.sleep(2000);

        // // explicit wait
        // WebDriverWait wait = new WebDriverWait(driver, 15);
        // wait .until(ExpectedConditions.visibilityOf(emailTextBox));

        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

        // System.out.println(emailTextBox);

        return this.VerifyUserLoggedIn(username);
    }

    // public void login(String string, String string2) {}

    private Boolean VerifyUserLoggedIn(String username) {
        try {
            // Find the username label (present on the top right of the page)
            // WebElement username_label = this.driver.findElement(By.className("username-text"));
            WebElement username_label =
                    SeleniumWrapper.findElementWithRetry(driver, By.className("username-text"), 3);
            return username_label.getText().equals(username);
        } catch (Exception e) {
            return false;
        }
    }
}
