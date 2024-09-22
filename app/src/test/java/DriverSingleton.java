package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.sql.Driver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.util.RetryAnalyzerCount;

public class DriverSingleton {

    //private instance of class
    private static DriverSingleton instanceOfSingletonBrowserClass = null;

    //constructor
    private RemoteWebDriver driver;

    private DriverSingleton() throws MalformedURLException {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        // driver = BrowserType.handler.getInstanceOfSingletonBrowserClass();
        driver.manage().window().maximize();
    }

    public static DriverSingleton getInstanceOfSingletonBrowserClass()
            throws MalformedURLException {
        if (instanceOfSingletonBrowserClass == null) {
            instanceOfSingletonBrowserClass = new DriverSingleton();
        }
        return instanceOfSingletonBrowserClass;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }
}

