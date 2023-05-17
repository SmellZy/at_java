package itstep.course_tasks.task_22.bo;

import io.appium.java_client.AppiumDriver;
import itstep.course_tasks.task_22.DriverManager;
import itstep.course_tasks.task_22.po.MobilePO;

public class MobileBO {
    AppiumDriver driver = new DriverManager().getInstance();
    MobilePO mobilePO = new MobilePO();

    public MobileBO calculate() throws InterruptedException {
        mobilePO.typeNumber1(driver).typeNumberPlus(driver).typeNumber5(driver).clickResultButton(driver).checkResult(driver);
        return this;
    }
}
