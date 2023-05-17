package task_12;

import itstep.course_tasks.task_12.bo.DemoBlazeBO;
import itstep.course_tasks.task_12.WebDriverSupplier;
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
