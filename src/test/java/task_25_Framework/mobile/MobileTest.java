package task_25_Framework.mobile;

import itstep.task_25_Framework.mobile.bo.MobileBO;
import org.testng.annotations.Test;

public class MobileTest {

    @Test
    void mobileSumTest() throws InterruptedException {
        MobileBO mobileBO = new MobileBO();
        mobileBO.calculate();
    }

    @Test
    void mobileMultiTest() throws InterruptedException {
        MobileBO mobileBO = new MobileBO();
        mobileBO.multiplicate();
    }

    @Test
    void mobileDivideTest() throws InterruptedException {
        MobileBO mobileBO = new MobileBO();
        mobileBO.divide();
    }
}
