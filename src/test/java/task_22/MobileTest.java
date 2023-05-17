package task_22;

import itstep.course_tasks.task_22.bo.MobileBO;
import org.testng.annotations.Test;

public class MobileTest {
    @Test
    void mobileText() throws InterruptedException {
        MobileBO mobileBO = new MobileBO();
        mobileBO.calculate();
    }
}
