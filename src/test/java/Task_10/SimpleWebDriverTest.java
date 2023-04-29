package Task_10;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SimpleWebDriverTest {
    private WebDriver driver;
    @BeforeTest
    void setup(){
        //Setup ChromeDriver using driver file and properties.
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        //WebDriver driver = new ChromeDriver();


        //Setup ChromeDriver using DriverManager.
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
    }
    @AfterTest
    void shutDown(){
        driver.close();
        driver.quit();
    }


    @Test
    void simpleWDTest(){
        driver.get("https://demoqa.com/dynamic-properties");
//        //1
//        WebElement elements = driver.findElement(By.("This text has random Id"));
//        Assert.assertTrue(elements.isDisplayed());
        //2
        WebElement changeColor = driver.findElement(By.id("colorChange"));
        Assert.assertTrue(changeColor.isDisplayed());
        //3
        WebElement visibleAfter5sec = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable((By.id("visibleAfter"))));
        Assert.assertTrue(visibleAfter5sec.isDisplayed());
    }
}
