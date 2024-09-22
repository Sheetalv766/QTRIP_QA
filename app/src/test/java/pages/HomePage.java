package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    RemoteWebDriver driver;

    @FindBy(xpath = "//input[@id='autocomplete']")
    private WebElement searchCity;

    // Search for a city that's not present
    @FindBy(xpath = "//*[text()='No City found']")
    private WebElement noCityFoundMsg;

    // Select Filters : hours
    @FindBy(xpath = "(//*[@class='form-control'])[1]")
    private WebElement hoursFilter;

    @FindBy(xpath = "(//*[@class='form-control'])[2]")
    private WebElement catgryFilter;

    @FindBy(xpath = "(//div[@class='ms-3'])")
    private WebElement clrFilters;

    @FindBy(xpath = "//*[@id='results']")
    private WebElement clickOncity;

    // private String url;

    private final String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    // 1. Navigate to Home Page of QTrip
    public void navigateToHomePage() {
        // if (!this.driver.getCurrentUrl().equals(this.url)) {
        // this.driver.get(this.url);
        // driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        SeleniumWrapper.navigatetoURL(driver, "https://qtripdynamic-qa-frontend.vercel.app/");
    }

    public boolean isUserLoggedIn() {
        return false;
    }

    public Boolean performLogout() {

        try {
            // Find and click on the Logout Button
            // WebElement logout_button = driver.findElement(By.xpath("//div[text()='Logout']"));
            // logout_button.click();
            WebElement logout_button = SeleniumWrapper.findElementWithRetry(driver,
                    By.xpath("//div[text()='Logout']"), 3);
            SeleniumWrapper.click(logout_button, driver);

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("css-1urhf6j"),
                    "Logout"));

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }
}


// EXTRA CODE
// // Search for a city that's not present
// public Boolean searchCityNotPresent(String cityName) {
// WebElement cityNotPresent = driver.findElement(By.xpath("//*[@id='results']"));
// cityNotPresent.click();
// // searchCity.sendKeys("Gao");

// WebDriverWait wait = new WebDriverWait(driver, 10);
// wait.until(
// ExpectedConditions.textToBePresentInElementValue(cityNotPresent, "No City Found"));

// return true;
// }

// // Search for a city that's present
// public boolean searchCityPresent(String cityName) throws InterruptedException {
// WebElement cityPresent = driver.findElement(By.xpath("//*[@id='results']"));
// cityPresent.click();
// // searchCity.click();
// Thread.sleep(2000);

// // searchCity.sendKeys(cityName);

// // WebDriverWait wait = new WebDriverWait(driver, 10);
// // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='results']")));
// // // clickOncity.click();
// return true;
// }

// public void clickOnCity() {
// return;
// }

// // select filters : hourd
// public boolean selectHoursFilter(String hours) {
// Select hoursSel = new Select(hoursFilter);
// WebElement hoursSelect = driver.findElement(By.xpath("(//select)[1]"));
// // hoursSel.selectByVisibleText(hours);
// hoursSelect.click();
// return true;
// }

// //select category
// public boolean selectCategoryFilter(String category) {
// Select categorySelect = new Select(catgryFilter);
// categorySelect.selectByVisibleText(category);
// return true;
// }

// public boolean clearFiltersCatgry() {
// clrFilters.click();
// return true;
// }
