package task_22;

import io.appium.java_client.AppiumDriver;
import itstep.task_22.DriverManager;
import itstep.task_22.bo.MobileBO;
import org.testng.annotations.Test;

public class MobileTest {
    @Test
    void mobileText() throws InterruptedException {
        MobileBO mobileBO = new MobileBO();
        mobileBO.calculate();
    }
}
