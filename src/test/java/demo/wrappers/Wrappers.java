package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     ChromeDriver driver;

     public Wrappers(ChromeDriver driver) {
         this.driver = driver;
     }
 
     public WebElement waitForElement(By locator) {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         return wait.until(ExpectedConditions.elementToBeClickable(locator));
     }
 
     public void clickElement(By locator) {
         waitForElement(locator).click();
     }
 
     public void enterText(By locator, String text) {
         WebElement element = waitForElement(locator);
         element.clear();
         element.sendKeys(text);
     }
}
