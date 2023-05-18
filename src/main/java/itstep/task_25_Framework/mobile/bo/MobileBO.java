package itstep.task_25_Framework.mobile.bo;

import io.appium.java_client.AppiumDriver;
import itstep.task_25_Framework.mobile.DriverManager;
import itstep.task_25_Framework.mobile.po.MobilePO;

public class MobileBO {
    AppiumDriver driver = new DriverManager().getInstance();
    MobilePO mobilePO = new MobilePO();

    public MobileBO calculate() throws InterruptedException {
        mobilePO.typeNumber1(driver).typeNumberPlus(driver).typeNumber5(driver).clickResultButton(driver).checkResult(driver).clearResult(driver);
        return this;
    }

    public MobileBO multiplicate() throws InterruptedException {
        mobilePO.typeNumber3(driver).tapMultiplicate(driver).typeNumber7(driver).clickResultButton(driver).checkMultiplicateResult(driver).clearResult(driver);
        return this;
    }

    public MobileBO divide() throws InterruptedException {
        mobilePO.typeNumber30(driver).tapDivide(driver).typeNumber3(driver).clickResultButton(driver).checkDivideResult(driver).clearResult(driver);
        return this;
    }
}
