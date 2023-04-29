package task_12;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import itstep.task_12.bo.DemoBlazeBO;
import itstep.task_12.WebDriverSupplier;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SingUpLoginTestTask12 {

    @BeforeTest
    void setup(){
        WebDriverSupplier.setupDriver();
    }

    @Test
    void endToEndTest() throws InterruptedException {
        // target https://www.demoblaze.com
        DemoBlazeBO demoBlazeBO = new DemoBlazeBO();
        demoBlazeBO.signUpRandomUser()
                .loginUser()
                .addToCart()
                .verifyOrder();
    }

    @AfterTest
    void shutDown(){
        WebDriverSupplier.closeDriver();
    }
}
