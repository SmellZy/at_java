package itstep.task_22.po;

import io.appium.java_client.AppiumDriver;
import itstep.task_22.bo.MobileBO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MobilePO {
    private final String button_num1 = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]";
    private final String button_plus = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.Button[4]";
    private final String button_num5 = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.Button[2]";
    private final String button_result = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout[2]/android.widget.Button";
    private final String result_row = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView";


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


}
