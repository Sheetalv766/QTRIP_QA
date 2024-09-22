package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {
    public static boolean click(WebElement elemToClick, WebDriver driver)
            throws InterruptedException {
                return false;
    /*
     * Check if element exists before click 
     * Scroll into view for a web element before trying to click it 
     * Return true if the action was successful , else return false
     */

    }

    public static boolean sendKeys(WebElement inputBox, String keysToSend){
        //Clear existing elements of an input box before typing in new text

        return false;

    }

    public static boolean navigatetoURL(WebDriver driver, String url){
        //Navigate to the new url only if the given url is different from the existing url

        return false;
    }

    public static WebElement findElementWithRetry(WebDriver driver, By by, int retryCount){
        return null;
        //Try to find an element
        // If the element is not available , retry upto 3 times before failing
    }

}

