package task_13;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import itstep.task_12.WebDriverSupplier;
import itstep.task_12.bo.DemoBlazeBO;
import itstep.task_13.IExecutionListener;
import itstep.task_13.IInvoke;
import itstep.task_13.SuiteListener;
import itstep.task_13.TestListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({SuiteListener.class, TestListener.class, IExecutionListener.class, IInvoke.class, UniversalVideoListener.class})
public class SingUpLoginTestTask13 {

    @BeforeTest
    void setup(){
        WebDriverSupplier.setupDriver();
    }

    @Video
    @Test
    void endToEndTest() throws InterruptedException {
        // target https://www.demoblaze.com
        DemoBlazeBO demoBlazeBO = new DemoBlazeBO();
        demoBlazeBO.signUpRandomUser()
                .loginUser()
                .addToCart()
                .verifyOrder();
    }

    @Video
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
