package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 {

    private HomePage homePage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private AdventurePage adventurePage;
    private HistoryPage historyPage;

    static RemoteWebDriver driver;
    static ExtentTest test;

    @BeforeTest(alwaysRun = true, enabled = true)
    public static void createDriver() throws MalformedURLException {
        // driver location here
        DriverSingleton ds_1 = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = ds_1.getDriver();
    }

    @Test(description = "Verify that adventure booking and cancellation works fine",
            dataProvider = "data-provider", dataProviderClass = DP.class, priority = 3,
            groups = {"Booking and Cancellation Flow"})
    public void TestCase03(String email, String password, String city, String location, String area,
            String date, String persons) throws InterruptedException {

        test = ReportSingleton.startTest("TestCase03");

        // instantiate page objects
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        // loginPage = new LoginPage(driver);
        adventurePage = new AdventurePage(driver);
        historyPage = new HistoryPage(driver);

        // click on the history page
        historyPage = new HistoryPage(driver);
        // // historyPage.cancelReserveation();

        System.out.println("Email : " + email);
        System.out.println("Password : " + password);
        System.out.println("City : " + city);
        System.out.println("Location : " + location);
        System.out.println("Area : " + area);
        System.out.println("Date : " + date);
        System.out.println("Persons : " + persons);
    }

    // SCREENSHOT
    public static String capture(WebDriver driver) throws IOException {

        // Component that captures a screenshot
        // of the current WebDriver instance and stores it.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Component that returns a File object
        // of the screenshot to be stored onto a file variable
        File Dest = new File(System.getProperty("user.dir") + "/QKARTImages/"
                + System.currentTimeMillis() + ".png");

        // Component that creates a destination folder
        // named QKARTImages within the ‘app’ folder and
        // stores the file name as the current system time.
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }

    @AfterSuite
    public void tearDown() {
        ReportSingleton.flushReport();
    }
}

// // cancel the reservation
// historyPage.cancelReserveation();

// // refresh the page
// driver.navigate().refresh();

// // check if transaction id is removed
// boolean isIdRemoved = historyPage.isIdRemoved(transactionID);
// assert isIdRemoved : "Transaction id is not removed";

// navigate to qtrip
// homePage.navigateToHomePage();

// create a new User
// registerPage.registerNewUser(email, password);

// search for an adventure
// adventurePage.bookingAndCancellation(city, date);

// enter name and date and reserve the adventure-->bookingAndCancellation
// verify that adventure booking was successful-->bookingAndCancellation
