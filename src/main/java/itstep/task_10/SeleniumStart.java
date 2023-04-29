package itstep.task_10;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumStart {
    public static void main(String[] args) {


        //Setup ChromeDriver using driver file and properties.
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        //WebDriver driver = new ChromeDriver();


        //Setup ChromeDriver using DriverManager.
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/dynamic-properties");
        //1
//        WebElement elements = driver.findElement(By.linkText("This text has random Id"));
//        System.out.println(elements.getRect());
        //2
        WebElement changeColor = driver.findElement(By.id("colorChange"));
        changeColor.click();
        System.out.println("click "+ changeColor);
        //3
        WebElement visibleAfter5sec = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable((By.id("visibleAfter"))));
        System.out.println("visible after 5 sec: " + visibleAfter5sec.getText());
        driver.close();
        driver.quit();
    }
}
