package itstep.task_25_Framework.parallel_driver_pool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ParallelDriverPool {
    private static ThreadLocal<WebDriver> chromeDriverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> firefoxDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getChromeDriver() {
        WebDriver driver = chromeDriverThreadLocal.get();
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
            driver = new ChromeDriver();
            chromeDriverThreadLocal.set(driver);
        }
        return driver;
    }

    public static WebDriver getFirefoxDriver() {
        WebDriver driver = firefoxDriverThreadLocal.get();
        if (driver == null) {
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
            driver = new FirefoxDriver();
            firefoxDriverThreadLocal.set(driver);
        }
        return driver;
    }
}
