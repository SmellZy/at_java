package task_11;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import itstep.Task_11.bo.DemoBlazeBO;
import itstep.Task_11.po.HomePage;
import itstep.Task_11.po.ProductPage;
import itstep.Task_11.po.SingUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class EndToEndTest {

    private WebDriver driver;
    @BeforeTest
    void setup(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    void endToEndTest() throws InterruptedException {
        // target https://www.demoblaze.com
        DemoBlazeBO demoBlazeBO = new DemoBlazeBO(driver);
        demoBlazeBO.signUpRandomUser()
                .loginUser()
                .addToCart()
                .verifyOrder();
    }

    @AfterTest
    void shutDown(){
        driver.close();
        driver.quit();
    }
}
