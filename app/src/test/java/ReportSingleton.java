package qtriptest;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ReportSingleton {

    private static ExtentReports extent;
    private static ExtentTest test;

    private ReportSingleton() {
        // Private constructor to prevent instantiation
    }

    public static String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String reportPath = System.getProperty("user.dir") + File.separator + "test-reports" + File.separator + "extent-report-" + timestamp + ".html";
            extent = new ExtentReports(reportPath, true);
        }
        return extent;
    }

    public static ExtentTest startTest(String testName) {
        test = extent.startTest(testName);
        return test;
    }

    public static void endTest() {
        extent.endTest(test);
    }

    public static void flushReport() {
        extent.flush();
    }
}
