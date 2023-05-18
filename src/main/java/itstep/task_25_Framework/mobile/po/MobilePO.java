package itstep.task_25_Framework.mobile.po;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MobilePO {

    public MobilePO typeNumber1(AppiumDriver driver) {
        System.out.println();
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btn1")).click();
        return this;
    }


    public MobilePO typeNumberPlus(AppiumDriver driver) {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btnAdd")).click();
        return this;
    }

    public MobilePO typeNumber5(AppiumDriver driver) {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btn5")).click();
        return this;
    }

    public MobilePO clickResultButton(AppiumDriver driver) {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btnEqual")).click();
        return this;
    }

    public MobilePO checkResult(AppiumDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        WebElement result = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[5]"));
        String resultString = result.getAttribute("text");
        System.out.println(resultString);
        Assert.assertEquals(resultString, "6");
        return this;
    }


    public MobilePO clearResult(AppiumDriver driver) {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btnC")).click();
        return this;
    }

    public MobilePO typeNumber3(AppiumDriver driver) {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btn3")).click();
        return this;
    }

    public MobilePO tapMultiplicate(AppiumDriver driver) {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btnMul")).click();
        return this;
    }

    public MobilePO typeNumber7(AppiumDriver driver) {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btn7")).click();
        return this;
    }

    public MobilePO checkMultiplicateResult(AppiumDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        WebElement result = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[5]"));
        String resultString = result.getAttribute("text");
        System.out.println(resultString);
        Assert.assertEquals(resultString, "21");
        return this;
    }

    public MobilePO typeNumber30(AppiumDriver driver) throws InterruptedException {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btn3")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btn0")).click();
        return this;
    }

    public MobilePO tapDivide(AppiumDriver driver) {
        driver.findElement(By.id("mobi.appplus.calculator.plus:id/btnDiv")).click();
        return this;
    }

    public MobilePO checkDivideResult(AppiumDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        WebElement result = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[5]"));
        String resultString = result.getAttribute("text");
        System.out.println(resultString);
        Assert.assertEquals(resultString, "10");
        return this;
    }
}
