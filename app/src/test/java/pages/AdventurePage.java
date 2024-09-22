package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AdventurePage {

        private RemoteWebDriver driver;
        private WebDriverWait wait;

        public AdventurePage(RemoteWebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, 10);
        }

        public void bookingAndCancellation(String adventureName, String date)
                        throws InterruptedException {
                // driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
                SeleniumWrapper.navigatetoURL(driver,
                                "https://qtripdynamic-qa-frontend.vercel.app/");

                // search an adventure
                // search a city text box
                // WebElement searchBox = driver
                // .findElement(By.xpath("//input[@placeholder='Search a City ']"));
                // searchBox.sendKeys(adventureName);
                // searchBox.submit();
                WebElement searchBox = SeleniumWrapper.findElementWithRetry(driver,
                                By.xpath("//input[@placeholder='Search a City ']"), 3);
                SeleniumWrapper.sendKeys(searchBox, adventureName);
                searchBox.submit();

                // select adventure
                // all the city mention in the homepage
                WebElement adven = wait.until(ExpectedConditions
                                .elementToBeClickable(By.xpath("//*[@class='tile']")));
                // adven.click();
                SeleniumWrapper.click(adven, driver);


                // Enter Name and Date and Reserve the adventure
                // WebElement items = driver.findElement(By.xpath("//div[@class='activity-card']"));
                // items.click();
                WebElement items = SeleniumWrapper.findElementWithRetry(driver,
                                By.xpath("//div[@class='activity-card']"), 3);
                SeleniumWrapper.click(items, driver);

                // name textbox
                // WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
                // name.sendKeys("sheetal");
                WebElement name = SeleniumWrapper.findElementWithRetry(driver,
                                By.xpath("//input[@name='name']"), 3);
                SeleniumWrapper.sendKeys(name, "sheetal");

                // pick a date
                // WebElement dateIn = driver.findElement(By.xpath("//*[@id='myForm']/input[2]"));
                // dateIn.sendKeys(date);
                // dateIn.click();
                WebElement dateIn = SeleniumWrapper.findElementWithRetry(driver,
                                By.xpath("//*[@id='myForm']/input[2]"), 3);
                SeleniumWrapper.sendKeys(dateIn, date);
                dateIn.click();

                // WebElement person = driver.findElement(By.xpath("//input[@name='person']"));
                // person.sendKeys("1");
                WebElement person = SeleniumWrapper.findElementWithRetry(driver,
                                By.xpath("//input[@name='person']"), 3);
                SeleniumWrapper.sendKeys(person, "1");

                // WebElement reserve = driver.findElement(By.xpath("//button[text()='Reserve']"));
                // reserve.click();
                WebElement reserve = SeleniumWrapper.findElementWithRetry(driver,
                                By.xpath("//button[text()='Reserve']"), 3);
                SeleniumWrapper.click(reserve, driver);

                // Verify that the adventure booking was successful
                WebElement successfulMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[@class='alert alert-success']")));

                // WebElement successfulMsg = driver.findElement(By.xpath("//div[@class='alert
                // alert-success']"));

                // Click on the history page
                // WebElement historyLinkPage =
                // driver.findElement(By.xpath("(//a['href='../reservations/'])[4]"));
                // historyLinkPage.click();
                WebElement historyLinkPage = SeleniumWrapper.findElementWithRetry(driver,
                                By.xpath("(//a['href='../reservations/'])[4]"), 3);
                SeleniumWrapper.click(historyLinkPage, driver);

                // repeat the process of booking
                repeatBookingProcess(adventureName, date);
        }

        private void repeatBookingProcess(String adventureName, String date)
                        throws InterruptedException {
                for (int i = 0; i < 1; i++) {

                        // repeat the steps 3-5
                        // WebElement searchBox =
                        // driver.findElement(By.xpath("//*[text()='searchBox']"));
                        // searchBox.sendKeys(adventureName);
                        WebElement searchBox = SeleniumWrapper.findElementWithRetry(driver,
                                        By.xpath("//*[text()='searchBox']"), 3);
                        SeleniumWrapper.sendKeys(searchBox, adventureName);
                        searchBox.submit();

                        WebElement adven = wait.until(ExpectedConditions
                                        .elementToBeClickable(By.className("adventure")));
                        // adven.click();
                        SeleniumWrapper.click(adven, driver);

                        // WebElement name = driver.findElement(By.id("name"));
                        // name.sendKeys("Shee");
                        WebElement name = SeleniumWrapper.findElementWithRetry(driver,
                                        By.id("name"), 3);
                        SeleniumWrapper.sendKeys(name, "Shee");

                        // WebElement dateIn = driver.findElement(By.id("date"));
                        // dateIn.sendKeys(date);
                        WebElement dateIn = SeleniumWrapper.findElementWithRetry(driver,
                                        By.id("date"), 3);
                        SeleniumWrapper.sendKeys(dateIn, date);

                        // WebElement reserve = driver.findElement(By.id("reserveButton"));
                        // reserve.click();
                        WebElement reserve = SeleniumWrapper.findElementWithRetry(driver,
                                        By.id("reserveButton"), 3);
                        SeleniumWrapper.click(reserve, driver);

                        WebElement successfulMsg = wait.until(ExpectedConditions
                                        .visibilityOfElementLocated(By.id("success message")));

                        // WebElement historyLinkPage = driver.findElement(By.id("link"));
                        // historyLinkPage.click();
                        WebElement historyLinkPage = SeleniumWrapper.findElementWithRetry(driver,
                                        By.id("link"), 3);
                        SeleniumWrapper.click(historyLinkPage, driver);
                }
        }
}
