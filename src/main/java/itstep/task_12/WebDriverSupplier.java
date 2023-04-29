package itstep.task_12;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverSupplier {
    public static WebDriver driver;


    public static void closeDriver() {
        driver.close();
        driver.quit();
    }

    public static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
