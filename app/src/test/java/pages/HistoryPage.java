
package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HistoryPage {

    RemoteWebDriver driver;
    private WebDriverWait wait;

    public HistoryPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // Note down the transaction ID
    // 5fab1816984d4460
    public String transactionID() {
        // WebElement transIdElem =
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//th)[10]")));
        WebElement transIdElem =
                SeleniumWrapper.findElementWithRetry(driver, By.xpath("(//th)[10]"), 3);
        String transID = transIdElem.getText();
        return transID;
    }

    // Cancel the Reservation
    public void cancelReserveation() throws InterruptedException {
        // WebElement cancel = driver.findElement(By.xpath("//button[text()='Cancel']"));
        // cancel.click();
        WebElement cancel = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//button[text()='Cancel']"), 3);
        SeleniumWrapper.click(cancel, driver);
    }
    // Refresh the page
    // driver.navigate().refresh();

    // Check if the transaction ID is removed
    public boolean isIdRemoved(String transID) {
        try {
            wait.until(ExpectedConditions
                    .invisibilityOfElementLocated(By.xpath("//div[@id='no-reservation-banner']")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
