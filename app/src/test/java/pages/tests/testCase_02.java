package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 {
    private HomePage homePage;
    private AdventurePage adventurePage;

    static RemoteWebDriver driver;
    static ExtentTest test;

    @BeforeTest(alwaysRun = true, enabled = true)
    public static void createDriver() throws MalformedURLException {
        // driver location here
        DriverSingleton ds_1 = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = ds_1.getDriver();
    }

    @BeforeSuite
    public void setExtentReport() {
        // initialize extent reports
        ReportSingleton.getInstance();
    }

    @Test(description = "Verify that Search and filters work fine", dataProvider = "data-provider",
            dataProviderClass = DP.class, priority = 2, groups = {"Search and Filter flow"})
    public void TestCase02(String cityName, String activity, String duration, String startDate,
            String endDate) throws InterruptedException {

        test = ReportSingleton.startTest("TestCase02");

        // instances of pages--> important line of code
        homePage = new HomePage(driver);

        System.out.println("City name : " + cityName);
        System.out.println("Activity : " + activity);
        System.out.println("Duration : " + duration);
        System.out.println("Start date : " + startDate);
        System.out.println("End date : " + endDate);
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

    @AfterTest(enabled = false)
    public void quitDriver() throws MalformedURLException {
        driver.close();
        driver.quit();
        System.out.println("Failure" + "driver" + "Quitting driver" + "Success");
    }

    @AfterSuite
    public void tearDown() {
        ReportSingleton.flushReport();
    }
}


// homePage.navigateToHomePage();
// driver.get("https://qtripdynamic-qa-frontend.vercel.app/");

// adventurePage = new AdventurePage(driver);

// Visit the home page and log out the logged in user
// HomePage home = new HomePage(driver);
// home.navigateToHomePage();
// home.performLogout();

// public void searchCityNotPresent() {
// homePage.navigateToHomePage();
// Assert.assertTrue(homePage.searchCityNotPresent("Not Exists"));
// }

// public void searchCityPresent() throws InterruptedException {
// homePage.navigateToHomePage();
// homePage.searchCityPresent("Exists");
// Assert.assertTrue(homePage.searchCityPresent("Exists"));
// }

// public void testFltrByhrs() throws InterruptedException {
// homePage.navigateToHomePage();
// homePage.selectHoursFilter("2 Hours");
// Assert.assertTrue(homePage.selectHoursFilter("Displayed"));
// }

// public void testFltrByCtgry() throws InterruptedException {
// homePage.navigateToHomePage();
// homePage.selectCategoryFilter("Adventure");
// Assert.assertTrue(homePage.selectCategoryFilter("Displayed"));
// }

// public void testClrFltrCtgry() throws InterruptedException {
// homePage.navigateToHomePage();
// homePage.selectHoursFilter("2 Hours");
// homePage.selectCategoryFilter("Adventure");
// homePage.clearFiltersCatgry();
// Assert.assertTrue(homePage.clearFiltersCatgry());
// }
