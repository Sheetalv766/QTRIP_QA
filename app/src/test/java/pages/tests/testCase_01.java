package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
// import qtriptest.pages.RegisterPage.Register;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class testCase_01 {

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

	@Test(description = "Verify user registration - login - logout", dataProvider = "data-provider",
			dataProviderClass = DP.class, priority = 1, groups = {"Login Flow"})
	@Parameters({"userName", "password"})
	public static void TestCase01(@Optional("testuser@gmail.com") String userName,
			@Optional("abc@123") String password) throws InterruptedException {

		// TestCase01
		test = ReportSingleton.startTest("TestCase01");

		// Visit the Registration page and register a new user
		RegisterPage register = new RegisterPage(driver);
		register.navigateToRegisterPage();
		register.registerUser(userName, password, true);

		// Save the last generated username
		userName = register.lastGeneratedUserName;

		// Visit the login page and login with the previuosly registered user
		LoginPage login = new LoginPage(driver);
		login.navigateToLoginPage();
		login.performLogin(userName, password);

		// Visit the home page and log out the logged in user
		HomePage home = new HomePage(driver);
		home.performLogout();

		home.navigateToHomePage();

		// Thread. sleep (3000);
		String username = register.lastGeneratedUserName;

		if (home.isUserLoggedIn()) {
			System.out.println("Page test navigation to register page success");
		} else {
			System.out.println("Page test navigation to login page failed");
		}

		if (!home.isUserLoggedIn()) {
			System.out.println("Successfully verified that the user is Logged out ");
		} else {
			System.out.println("Failure in verification of user Logout ");
		}
	}

	// try {
	// assertTrue(status);
	// test.log(LogStatus.PASS, "LOGIN PASSED");
	// } catch (AssertionError e) {
	// // Add a log statement that records a screenshot of test case failure.
	// test.log(LogStatus.FAIL, test.addScreenCapture(capture(driver))
	// + "LOGIN FAILED, reason : " + e.getMessage());
	// }
	// }

	// SCREENSHOT
	public static String capture(WebDriver driver) throws IOException {

		// Component that captures a screenshot
		// of the current WebDriver instance and stores it.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Component that returns a File object
		// of the screenshot to be stored onto a file variable
		File Dest = new File(
				System.getProperty("user.dir") + "/IMAGES/" + System.currentTimeMillis() + ".png");

		// Component that creates a destination folder
		// named QKARTImages within the ‘app’ folder and
		// stores the file name as the current system time.
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	// Quit webdriver after Unit Tests
	@AfterClass(enabled = false)
	public void quitDriver() throws MalformedURLException {
		driver.close();
		driver.quit();
		System.out.println("Failure in verification of user logout" + "driver" + "Quitting driver"
				+ "Success");
	}

	@AfterSuite
	public void tearDown() {
		ReportSingleton.flushReport();
	}
}



// home.navigateToHomePage();
// System.out.println("Pass");

// @Test(description = "Verify user registration - login - logout", enabled = true,
// dataProvider
// = "DP", dataProviderClass = DP.class)
// public static void TestCase01() {
// Assertion assertion = new Assertion();
// logStatus("Page test", "navigation to register page", "started");
// try {
// RegisterPage registerUser = new RegisterPage(driver);
// registerUser.navigateToHomePage();

// String expectedUrl = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
// String actualUrl = driver.getCurrentUrl();

// assertion.assertEquals(actualUrl, expectedUrl,
// "Actual" + actualUrl + "does not match as expected one" + expectedUrl);
// } catch (Exception e) {
// logStatus("Page test", "navigation to register page", "failed");
// e.printStackTrace();
// }
// }


// @Test(description = "Verify functionality of - navigate to login page", enabled = true)
// public static void testLogin_navigateToLoginPage() {
// Assertion assertion = new Assertion();
// logStatus("Page test", "navigation to login page", "started");
// try {
// RegisterPage register = new RegisterPage(driver);
// register.navigateToHomePage();
// assertion.assertTrue(driver.getCurrentUrl()
// .equals("https://qtripdynamic-qa-frontend.vercel.app/pages/login/"));
// logStatus("Page test", "navigation to login page", "success");
// } catch (Exception e) {
// logStatus("Page test", "navigation to login page", "failed");
// e.printStackTrace();
// }
// }


// @Test(description = "Verify functionality of - perform login ", enabled = true)
// public static void cltestLogin_performLogin() {
// try {
// Assertion assertion = new Assertion();
// logStatus("Page test", "perform login ", "started");

// RegisterPage register = new RegisterPage(driver);
// register.navigateToHomePage();
// register.registerUser("testuser@gmail.com", "abc@123", true);
// Thread.sleep(3000);
// LoginPage login = new LoginPage(driver);
// login.navigateToLoginPage();
// Boolean status = login.performLogin("testuser@gmail.com", "abc@123");
// if (status) {
// throw new Exception("Registered Not Done");
// }
// logStatus("Page test", "perform login ", "success");
// } catch (Exception e) {
// logStatus("Page test", "perform login ", "failed");
// e.printStackTrace();
// }
// }

// @Test(description = "Verify functionality of - verification of user login ", enabled =
// true)
// public static void testLogin_verifyUserLoggedIn() {
// try {
// Assertion assertion = new Assertion();
// logStatus("Page test", "perform login ", "started");
// RegisterPage register = new RegisterPage(driver);
// register.navigateToHomePage();
// register.registerUser("TESTUSER@gmail.com", "123456", false);
// Thread.sleep(3000);
// LoginPage login = new LoginPage(driver);
// login.navigateToLoginPage();
// Boolean status = login.performLogin("TESTUSER@gmail.com", "123456");
// if (status) {
// logStatus("Page test", "verify user logged in", "success");
// } else {
// throw new Exception("Not Logged in ");
// }
// } catch (Exception e) {
// logStatus("Page test", "Not logged in", "success");
// }
// }



// @Test
// public void testQTrip() {
// driver.get("https://qtripdynamic-qa-frontend.vercel.app");

// searchCity("Not any city");
// noCityFoundMsg("No Matches Found");
// searchForCityIs("Exists");
// assertTextPresent("Matches Found");
// clickOnCity("Existing city");
// hoursFilter("hours");
// assertDataDisplayed();
// catgryFilter("select category");
// assertDataDisplayed();
// clrFilters();
// assertDisplayedAll();
// }

// SCREENSHOT
// @AfterMethod(alwaysRun = true)
// public void afterMethod(ITestResult result) {
// if (result.getStatus() == ITestResult.FAILURE) {
// captureScreenshot(result.getMethod().getMethodName() + "FAILURE");
// }
// captureScreenshot("Final snapshot page" + result.getMethod().getMethodName());

// ReportSingleton.endTest();
// }

// private void captureScreenshot(String screenshotPage) {
// try {
// TakesScreenshot ts = (TakesScreenshot) driver;
// byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
// test.log(LogStatus.FAIL, test.addScreenCapture(screenshot), screenshotPage);
// } catch (Exception e) {
// e.printStackTrace();
// }

// }
