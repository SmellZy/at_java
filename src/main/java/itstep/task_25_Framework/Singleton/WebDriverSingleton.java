package itstep.task_25_Framework.Singleton;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
    private static WebDriver chromeDriver;
    private static WebDriver firefoxDriver;

    private WebDriverSingleton() {}

    public static WebDriver getChromeDriver() {
        if (chromeDriver == null) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            chromeDriver = new ChromeDriver(options);
        }
        return chromeDriver;
    }

    public static WebDriver getFirefoxDriver() {
        if (firefoxDriver == null) {
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
            firefoxDriver = new FirefoxDriver();
        }
        return firefoxDriver;
    }
}

