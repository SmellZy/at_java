package itstep.task_22.bo;

import io.appium.java_client.AppiumDriver;
import itstep.task_22.DriverManager;
import itstep.task_22.po.MobilePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MobileBO {
    AppiumDriver driver = new DriverManager().getInstance();
    MobilePO mobilePO = new MobilePO();

    public MobileBO calculate() throws InterruptedException {
        mobilePO.typeNumber1(driver).typeNumberPlus(driver).typeNumber5(driver).clickResultButton(driver).checkResult(driver);
        return this;
    }
}
