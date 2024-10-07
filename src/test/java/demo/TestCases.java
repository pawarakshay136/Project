package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;

import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }    

    @Test
    public void testCase01() throws InterruptedException {

        // Step 1: Navigate to this Google form.
    driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

    // Step 2: Wait until the first text box is clickable and locate it
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text' and @class='whsOnd zHQkBf' and @required]")));
    
    // Step 3: Fill in "Crio Learner" in the first text box
    WebElement nameField = driver.findElement(By.xpath("//input[@type='text' and @class='whsOnd zHQkBf' and @required]"));
    nameField.sendKeys("Crio Learner");

    // Step 4: Locate the second text box (textarea) and fill in a message with the current epoch time
    WebElement practicingAutomationField = driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c' and @required and @aria-label='Your answer']"));
    practicingAutomationField.click();
    String message = "I want to be the best QA Engineer! " + System.currentTimeMillis();
    practicingAutomationField.sendKeys(message); // Corrected variable used here

    // Step 5: Locate and select the radio button for "0 - 2"
    WebElement autoTestExpButton = driver.findElement(By.xpath("//div[@role='radio' and @aria-label='0 - 2']"));
    autoTestExpButton.click(); 

    // Step 6: List of labels for the checkboxes to select (only one declaration now)
    List<String> checkBoxLabels = Arrays.asList("Java", "Selenium", "TestNG");
    // Select checkboxes based on the labels
    for (String label : checkBoxLabels) {
        // Locate the checkbox based on the label text
        WebElement checkbox = driver.findElement(By.xpath("//label[.//span[contains(text(), '" + label + "')]]//div[@role='checkbox']"));

        // Check if it's not already selected
        if (!checkbox.getAttribute("aria-checked").equals("true")) {
            checkbox.click(); // Select the checkbox
            System.out.println(label + " checkbox selected.");
        } else {
            System.out.println(label + " checkbox is already selected.");
        }
    }

    // Step 7: Locate and select a dropdown option to indicate how you would like to be addressed
    WebElement selectDropDown = driver.findElement(By.xpath("//div[@role='listbox' and @jsname='W85ice']"));
    selectDropDown.click();
    Thread.sleep(3000);

    // Wait for the options to be visible
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));

    // Get all options
    List<WebElement> options = driver.findElements(By.xpath("//div[@role='listbox']//div[@role='option']"));

    // Desired option to select "Mr"
    String optionToSelect = "Mr";

    // Iterate through options to find and select the desired one
    for (WebElement option : options) {
        if (option.getText().equals(optionToSelect)) {
            option.click();  // Click the desired option
            break;           // Exit the loop once the option is found and clicked
        }
    }

    // Step 8: Enter date
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String txtDateText = currentDate.minusDays(7).format(formatter);

    WebElement dateTab = driver.findElement(By.xpath("//input[@type='date' and @jsname='YPqjbf']"));
    dateTab.sendKeys(txtDateText);

    // Step 9: Locate the hour input field and enter "07"
    WebElement hourInput = driver.findElement(By.xpath("//input[@jsname='YPqjbf' and @aria-label='Hour']"));
    hourInput.clear(); 
    hourInput.sendKeys("07");

    // Step 10: Locate the minute input field and enter "30"
    WebElement minuteInput = driver.findElement(By.xpath("//input[@jsname='YPqjbf' and @aria-label='Minute']"));
    minuteInput.clear(); 
    minuteInput.sendKeys("30"); // Enter minutes


    // Step 12: Locate and click the Submit button to submit the form
    WebElement submitButton = driver.findElement(By.xpath("//span[text()='Submit']"));
    submitButton.click();
    Thread.sleep(5000);  // Sleep to wait for the submission process to complete

    // Step 13: Print the final submission confirmation text
    WebElement finalText = driver.findElement(By.xpath("//div[@class='vHW8K']"));
    System.out.println(finalText.getText()); 
       
    }


    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }

}