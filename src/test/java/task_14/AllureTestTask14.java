package task_14;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import itstep.task_12.WebDriverSupplier;
import itstep.task_12.bo.DemoBlazeBO;
import itstep.task_13.IExecutionListener;
import itstep.task_13.IInvoke;
import itstep.task_13.SuiteListener;
import itstep.task_13.TestListener;
import itstep.task_14.AllureListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureListener.class})
public class AllureTestTask14 {

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

    @Test
    void endToEndFailTest() throws InterruptedException {
        // target https://www.demoblaze.com
        DemoBlazeBO demoBlazeBO = new DemoBlazeBO();
        demoBlazeBO.signUpRandomUser()
                .loginUser()
                .addToCart()
                .verifyOrder()
                .fail();
    }

    @AfterTest
    void shutDown(){
        WebDriverSupplier.closeDriver();
    }
}
